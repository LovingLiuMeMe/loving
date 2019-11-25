package cn.lovingliu.lovingmall.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-13
 */
@Data
public class ActivityItemDTO {
    private Long activityItemId;

    @NotNull(message = "必须指定关联活动")
    private Long activityId;

    @NotNull(message = "必须选择商品")
    private Long goodsId;
}
