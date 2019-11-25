package cn.lovingliu.lovingmall.service;

import cn.lovingliu.lovingmall.mbg.model.Order;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-05
 */
public interface OrderService {
    List<Order> listWithDeletedStatus(int pageNum,int pageSize,String orderBy,String orderType,Long userId,Integer deletedStatus);
    Order info(Long orderId);
    Long save(Order order);
    Integer update(Order order);
    Integer deleteByOrderIds(Long[] orderIds);
    void cancelOrderWithTimeOut();
}
