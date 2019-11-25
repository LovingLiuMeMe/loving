package cn.lovingliu.lovingmall.dto;

import lombok.Data;

/**
 * @Author：LovingLiu
 * @Description: 订单详情的DTO
 * @Date：Created in 2019-11-07
 */
@Data
public class OrderItemDTO {
    private Long goodsId;
    private Integer changeAmount;

    public OrderItemDTO(Long goodsId, Integer changeAmount){
        this.goodsId = goodsId;
        this.changeAmount = changeAmount;
    }
}
