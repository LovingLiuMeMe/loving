package cn.lovingliu.lovingmall.mbg.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable {
    /**
     * 订单表主键id
     *
     * @mbg.generated
     */
    private Long orderId;

    /**
     * 订单号
     *
     * @mbg.generated
     */
    private String orderNo;

    /**
     * 用户主键id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     * 订单总价
     *
     * @mbg.generated
     */
    private Integer totalPrice;

    /**
     * 支付状态:0.未支付,1.支付成功,-1:支付失败
     *
     * @mbg.generated
     */
    private Byte payStatus;

    /**
     * 0.无 1.支付宝支付 2.微信支付
     *
     * @mbg.generated
     */
    private Byte payType;

    /**
     * 支付时间
     *
     * @mbg.generated
     */
    private Date payTime;

    /**
     * 订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭
     *
     * @mbg.generated
     */
    private Byte orderStatus;

    /**
     * 订单body
     *
     * @mbg.generated
     */
    private String extraInfo;

    /**
     * 收货人姓名
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * 收货人手机号
     *
     * @mbg.generated
     */
    private String userPhone;

    /**
     * 收货人收货地址
     *
     * @mbg.generated
     */
    private String userAddress;

    /**
     * 删除标识字段(0-未删除 1-已删除)
     *
     * @mbg.generated
     */
    private Byte isDeleted;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 最新修改时间
     *
     * @mbg.generated
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

}