package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.enums.ExceptionCodeEnum;
import cn.lovingliu.lovingmall.exception.LovingMallException;
import cn.lovingliu.lovingmall.mbg.mapper.OrderItemMapper;
import cn.lovingliu.lovingmall.mbg.model.OrderItem;
import cn.lovingliu.lovingmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 订单详情
 * @Date：Created in 2019-11-05
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItem> listWithOrderId(Long orderId) {
        if(orderId == null){
            throw new LovingMallException(ExceptionCodeEnum.PARAM_ERROR);
        }
        return orderItemMapper.selectByOrderId(orderId);
    }

    @Override
    public List<OrderItem> listWithOrderIdList(List<Long> orderIdList) {
        return orderItemMapper.selectByOrderIdList(orderIdList);
    }

    @Override
    public int saveList(List<OrderItem> orderItemList) {
        return orderItemMapper.insertSelectiveList(orderItemList);
    }
}
