package cn.lovingliu.lovingmall.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class OrderItem implements Serializable {
    /**
     * 订单关联购物项主键id
     *
     * @mbg.generated
     */
    private Long orderItemId;

    /**
     * 订单主键id
     *
     * @mbg.generated
     */
    private Long orderId;

    /**
     * 关联商品id
     *
     * @mbg.generated
     */
    private Long goodsId;

    /**
     * 下单时商品的名称(订单快照)
     *
     * @mbg.generated
     */
    private String goodsName;

    /**
     * 下单时商品的主图(订单快照)
     *
     * @mbg.generated
     */
    private String goodsCoverImg;

    /**
     * 下单时商品的价格(订单快照)
     *
     * @mbg.generated
     */
    private Integer sellingPrice;

    /**
     * 数量(订单快照)
     *
     * @mbg.generated
     */
    private Integer goodsCount;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 订单描述
     *
     * @mbg.generated
     */
    private String goodsIntro;

    private static final long serialVersionUID = 1L;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCoverImg() {
        return goodsCoverImg;
    }

    public void setGoodsCoverImg(String goodsCoverImg) {
        this.goodsCoverImg = goodsCoverImg;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGoodsIntro() {
        return goodsIntro;
    }

    public void setGoodsIntro(String goodsIntro) {
        this.goodsIntro = goodsIntro;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderItemId=").append(orderItemId);
        sb.append(", orderId=").append(orderId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsCoverImg=").append(goodsCoverImg);
        sb.append(", sellingPrice=").append(sellingPrice);
        sb.append(", goodsCount=").append(goodsCount);
        sb.append(", createTime=").append(createTime);
        sb.append(", goodsIntro=").append(goodsIntro);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}