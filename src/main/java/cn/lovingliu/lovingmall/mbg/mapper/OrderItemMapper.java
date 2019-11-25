package cn.lovingliu.lovingmall.mbg.mapper;

import cn.lovingliu.lovingmall.mbg.model.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Long orderItemId);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Long orderItemId);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    List<OrderItem> selectByOrderId(Long orderId);

    List selectByOrderIdList(@Param("orderIdList") List<Long> orderIdList);
    int insertSelectiveList(@Param("orderItemList") List<OrderItem> orderItemList);
}