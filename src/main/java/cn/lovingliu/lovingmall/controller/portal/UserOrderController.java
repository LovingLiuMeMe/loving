package cn.lovingliu.lovingmall.controller.portal;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.dto.OrderDTO;
import cn.lovingliu.lovingmall.enums.ExceptionCodeEnum;
import cn.lovingliu.lovingmall.exception.LovingMallException;
import cn.lovingliu.lovingmall.mbg.model.GoodsInfo;
import cn.lovingliu.lovingmall.mbg.model.Order;
import cn.lovingliu.lovingmall.mbg.model.OrderItem;
import cn.lovingliu.lovingmall.service.GoodsInfoService;
import cn.lovingliu.lovingmall.service.OrderItemService;
import cn.lovingliu.lovingmall.service.OrderService;
import cn.lovingliu.lovingmall.service.WebSocketServer;
import cn.lovingliu.lovingmall.vo.OrderVO;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author：LovingLiu
 * @Description: 买家的订单
 * @Date：Created in 2019-10-30
 */
@Api(tags = "UserOrderController",description = "买家订单服务")
@RestController
@RequestMapping("/userOrder")
@Slf4j
public class UserOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping("/list")
    public ServerResponse list(@RequestParam(value = "userId") Long userId){
        /**
         * 查询所以订单
         */
        List<Order> orderList = orderService.listWithDeletedStatus(0,0,null,null,userId, null);
        List<Long> orderIdList = orderList.stream().map(e -> e.getOrderId()).collect(Collectors.toList());
        /**
         * 查看订单中的商品信息
         */
        List<OrderItem> orderItemList = orderItemService.listWithOrderIdList(orderIdList);
        List<OrderVO> orderVOList = Lists.newArrayList();
        for (Order order : orderList) {
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order, orderVO);

            List<OrderItem> tempOrderItemList = Lists.newArrayList();
            for(OrderItem orderItem : orderItemList) {
                if(order.getOrderId() == orderItem.getOrderId()){
                    tempOrderItemList.add(orderItem);
                }
            }
            orderVO.setOrderItemList(tempOrderItemList);
            orderVOList.add(orderVO);
        }

        return ServerResponse.createBySuccess("获取成功", orderVOList);
    }

    @ApiOperation("获得订单详情(此接口有重复)")
    @GetMapping("/info")
    public ServerResponse<OrderVO> info(@ApiParam("订单ID")
                                        @RequestParam("orderId")
                                        Long orderId){
        OrderVO orderVO = new OrderVO();
        Order order = orderService.info(orderId);
        BeanUtils.copyProperties(order, orderVO);

        List<OrderItem> orderItemList = orderItemService.listWithOrderId(orderId);
        orderVO.setOrderItemList(orderItemList);
        return ServerResponse.createBySuccess("获取成功",orderVO);
    }
    @ApiOperation("删除订单")
    @PostMapping("/delete")
    public ServerResponse delete(@ApiParam("订单Id数组")
                                 @RequestParam Long[] orderIds){
        if(orderIds.length < 1){
            throw new LovingMallException(ExceptionCodeEnum.PARAM_ERROR);
        }
        int count = orderService.deleteByOrderIds(orderIds);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @ApiOperation("创建订单")
    @PostMapping("/save")
    public ServerResponse save(@RequestBody OrderDTO order){
        Order mainOrder = new Order();
        BeanUtils.copyProperties(order, mainOrder);
        // 1.生成订单号
        mainOrder.setOrderNo(UUID.randomUUID().toString());
        if(mainOrder.getOrderStatus() == 1){
            mainOrder.setPayTime(new Date());
        }
        // 2.创建订单
        Long orderId = orderService.save(mainOrder);

        if(orderId == null){
            throw new LovingMallException(ExceptionCodeEnum.ORDER_NOT_EXIT);
        }
        List<OrderItem> orderItemList = order.getOrderItemList();

        List<Long> goodsInfoIdList = orderItemList.stream().map(e -> {
            return e.getGoodsId();
        }).collect(Collectors.toList());

        // 3.获得订单商品列表
        List<GoodsInfo> goodsInfoList = goodsInfoService.ListByGoodsIdList(goodsInfoIdList);

        // 4.组装数据
        for (GoodsInfo goodsInfo: goodsInfoList) {
            for(OrderItem orderItem : orderItemList) {
                if(goodsInfo.getGoodsId().longValue() == orderItem.getGoodsId().longValue()){
                    BeanUtils.copyProperties(goodsInfo,orderItem);
                    orderItem.setOrderId(orderId);
                }
            }
        }

        // 5.保存数据
        log.warn("【处理之后的数据】=> {}",order);

        int count = orderItemService.saveList(orderItemList);
        if(count > 0){
            try{
                WebSocketServer.sendInfo("您有新的订单,请注意查收", null);
            }catch (IOException e){
                log.error("【websocket】通知报错 => ",e.getMessage());
            }
            return ServerResponse.createBySuccessMessage("下单成功");
        }else{
            return ServerResponse.createBySuccessMessage("下单失败");
        }
    }


}
