package cn.lovingliu.lovingmall.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author：LovingLiu
 * @Description:  关于时间的格式化和强转报错 https://www.cnblogs.com/w-essay/p/11453943.html
 * @Date：Created in 2019-11-05
 */
@Data
public class GoodsInfoDTO {
    private Long goodsId;
    @NotNull(message = "商品名称必须填写")
    private String goodsName;

    private String goodsIntro;

    @NotNull(message = "商品必须指定分类")
    private Long goodsCategoryId;

    private String goodsCoverImg;

    private String goodsCarousel;

    private Integer originalPrice;

    @NotNull(message = "商品售价必须填写")
    private Integer sellingPrice;

    @NotNull(message = "商品库存要指定")
    private Integer stockNum;

    private String tag;

    private Byte goodsSellStatus;
    private Integer createUser;

    private Integer updateUser;

    private String goodsDetailContent;

    private String configParam;
}
