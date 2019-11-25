package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.dto.OrderItemDTO;
import cn.lovingliu.lovingmall.enums.CommonCodeEnum;
import cn.lovingliu.lovingmall.enums.ExceptionCodeEnum;
import cn.lovingliu.lovingmall.exception.LovingMallException;
import cn.lovingliu.lovingmall.mbg.mapper.GoodsInfoMapper;
import cn.lovingliu.lovingmall.mbg.mapper.OrderMapper;
import cn.lovingliu.lovingmall.mbg.model.Order;
import cn.lovingliu.lovingmall.mbg.model.OrderItem;
import cn.lovingliu.lovingmall.service.OrderService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-05
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public List<Order> listWithDeletedStatus(int pageNum,int pageSize,String orderBy,String orderType,Long userId,Integer deletedStatus) {
        if(pageNum == 0 || pageSize == 0){
            return orderMapper.selectByIsDeleted(userId, deletedStatus);
        }else{
            PageHelper.startPage(pageNum, pageSize);
            PageHelper.orderBy(orderBy+" "+orderType);
            return orderMapper.selectByIsDeleted(userId, deletedStatus);
        }
    }

    @Override
    public Order info(Long orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if(order == null){
            throw new LovingMallException(ExceptionCodeEnum.ORDER_NOT_EXIT);
        }
        return order;
    }

    @Override
    public Long save(Order order) {
        int count = orderMapper.insertSelective(order);
        if(count > 0) {
            log.info("【用户下单成功】=> {}",count);
        }else {
            log.info("【用户下单失败】=> {}",count);
        }
        return order.getOrderId();
    }

    @Override
    public Integer update(Order order) {
        return orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public Integer deleteByOrderIds(Long[] orderIds) {
        return orderMapper.updateOrderIsDeleted(orderIds);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public void cancelOrderWithTimeOut() {
        Map<Long,Integer> orderItemDTOMap = new HashMap<>();
        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
        Set<Long> orderIdSet = new HashSet<>();
        /**
         * 1.获取当前时间之前的两小时时间
         */
        Date date = new Date();
        Calendar dar=Calendar.getInstance();
        dar.setTime(date);
        dar.add(java.util.Calendar.HOUR_OF_DAY, -2);
        date = dar.getTime();
        /**
         * 2.查询出所有的 超过两小时 未付款的订单
         */
        List<OrderItem> orderItemList = orderMapper.selectTimeOutOrderItem(date);
        if(orderItemList.size() > 0){
            /**
             * 3.数据封装
             */
            for (OrderItem orderItem : orderItemList) {
                // 3.1 订单ID
                orderIdSet.add(orderItem.getOrderId());
                // 3.2 key=goodsId value=数量
                Long goodsId = orderItem.getGoodsId();
                if(orderItemDTOMap.containsKey(goodsId)){
                    Integer tempAmount = orderItemDTOMap.get(goodsId);
                    orderItemDTOMap.put(goodsId,tempAmount + orderItem.getGoodsCount());
                }else {
                    orderItemDTOMap.put(goodsId,orderItem.getGoodsCount());
                }
            }
            orderItemDTOList = orderItemDTOMap.entrySet().stream().map(e -> new OrderItemDTO(e.getKey(),e.getValue())).collect(Collectors.toList());
            /**
             * 4.批量更新订单状态
             */
            int orderCount = orderMapper.updateOrderStatus(CommonCodeEnum.ORDER_STATUS_TIME_OUT.getCode(),orderIdSet);
            log.info("【定时关闭订单】数量:"+orderCount);
            /**
             * 5.批量归还库存
             * 注意:mysql不支持批操作 需要配置&allowMultiQueries=true 才可以
             */
            log.info("【参数是】数量:"+orderItemDTOList);
            int goodsCount = goodsInfoMapper.updateGoodsInfoStockNum(orderItemDTOList);
            log.info("【定时修改商品库存】数量:"+goodsCount);
        }
    }
}
