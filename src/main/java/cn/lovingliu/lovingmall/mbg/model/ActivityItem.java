package cn.lovingliu.lovingmall.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class ActivityItem implements Serializable {
    /**
     * 主页活动关联商品id
     *
     * @mbg.generated
     */
    private Long activityItemId;

    /**
     * 主页活动id
     *
     * @mbg.generated
     */
    private Long activityId;

    /**
     * 关联商品id
     *
     * @mbg.generated
     */
    private Long goodsId;

    /**
     * 商品的名称(订单快照)
     *
     * @mbg.generated
     */
    private String goodsName;

    /**
     * 商品的主图(订单快照)
     *
     * @mbg.generated
     */
    private String goodsCoverImg;

    /**
     * 活动价格
     *
     * @mbg.generated
     */
    private Integer sellingPrice;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getActivityItemId() {
        return activityItemId;
    }

    public void setActivityItemId(Long activityItemId) {
        this.activityItemId = activityItemId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", activityItemId=").append(activityItemId);
        sb.append(", activityId=").append(activityId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsCoverImg=").append(goodsCoverImg);
        sb.append(", sellingPrice=").append(sellingPrice);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}