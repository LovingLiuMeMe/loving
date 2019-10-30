package cn.lovingliu.lovingmall.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class GoodsInfo implements Serializable {
    /**
     * 商品表主键id
     *
     * @mbg.generated
     */
    private Long goodsId;

    /**
     * 商品名
     *
     * @mbg.generated
     */
    private String goodsName;

    /**
     * 商品简介
     *
     * @mbg.generated
     */
    private String goodsIntro;

    /**
     * 关联分类id
     *
     * @mbg.generated
     */
    private Long goodsCategoryId;

    /**
     * 商品主图
     *
     * @mbg.generated
     */
    private String goodsCoverImg;

    /**
     * 商品轮播图
     *
     * @mbg.generated
     */
    private String goodsCarousel;

    /**
     * 商品价格
     *
     * @mbg.generated
     */
    private Integer originalPrice;

    /**
     * 商品实际售价
     *
     * @mbg.generated
     */
    private Integer sellingPrice;

    /**
     * 商品库存数量
     *
     * @mbg.generated
     */
    private Integer stockNum;

    /**
     * 商品标签
     *
     * @mbg.generated
     */
    private String tag;

    /**
     * 商品上架状态 0-下架 1-上架
     *
     * @mbg.generated
     */
    private Byte goodsSellStatus;

    /**
     * 添加者主键id
     *
     * @mbg.generated
     */
    private Integer createUser;

    /**
     * 商品添加时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 修改者主键id
     *
     * @mbg.generated
     */
    private Integer updateUser;

    /**
     * 商品修改时间
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * 商品详情
     *
     * @mbg.generated
     */
    private String goodsDetailContent;

    private static final long serialVersionUID = 1L;

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

    public String getGoodsIntro() {
        return goodsIntro;
    }

    public void setGoodsIntro(String goodsIntro) {
        this.goodsIntro = goodsIntro;
    }

    public Long getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(Long goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public String getGoodsCoverImg() {
        return goodsCoverImg;
    }

    public void setGoodsCoverImg(String goodsCoverImg) {
        this.goodsCoverImg = goodsCoverImg;
    }

    public String getGoodsCarousel() {
        return goodsCarousel;
    }

    public void setGoodsCarousel(String goodsCarousel) {
        this.goodsCarousel = goodsCarousel;
    }

    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Byte getGoodsSellStatus() {
        return goodsSellStatus;
    }

    public void setGoodsSellStatus(Byte goodsSellStatus) {
        this.goodsSellStatus = goodsSellStatus;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getGoodsDetailContent() {
        return goodsDetailContent;
    }

    public void setGoodsDetailContent(String goodsDetailContent) {
        this.goodsDetailContent = goodsDetailContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsIntro=").append(goodsIntro);
        sb.append(", goodsCategoryId=").append(goodsCategoryId);
        sb.append(", goodsCoverImg=").append(goodsCoverImg);
        sb.append(", goodsCarousel=").append(goodsCarousel);
        sb.append(", originalPrice=").append(originalPrice);
        sb.append(", sellingPrice=").append(sellingPrice);
        sb.append(", stockNum=").append(stockNum);
        sb.append(", tag=").append(tag);
        sb.append(", goodsSellStatus=").append(goodsSellStatus);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", goodsDetailContent=").append(goodsDetailContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}