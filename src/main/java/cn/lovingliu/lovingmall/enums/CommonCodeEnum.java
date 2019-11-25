package cn.lovingliu.lovingmall.enums;

/**
 * @Author：LovingLiu
 * @Description: 响应结果
 * @Date：Created in 2019-10-29
 */
public enum CommonCodeEnum implements CodeEnum{
    // 请求
    REQUEST_SUCCESS(200,"请求成功"),
    REQUEST_FAIL(500,"请求失败"),

    // 商品
    PRODUCT_STATUS_ON_SELL(0,"上架"),
    PRODUCT_STATUS_OFF_SELL(1,"下架"),
    PRODUCT_CATEGORY_NO_DELETED(0,"未删除"),
    GETPRODUCT_CATEGORY_DELETED(1,"已删除"),

    // banner
    BANNER_STATUS_NO_DELETED(0,"未删除"),
    BANNER_STATUS_DELETED(1,"已删除"),
    // 订单状态
    ORDER_STATUS_NO_PAY(0,"未付款"),
    ORDER_STATUS_PAYED(1,"已付款"),
    ORDER_STATUS_EXPRESS(2,"已发货"),
    ORDER_STATUS_BUYER_CANCEL(-1,"客户已取消"),
    ORDER_STATUS_TIME_OUT(-2,"订单超时"),
    ORDER_STATUS_SELLER_CANCEL(-3,"商家已取消"),

    // 收货地址状态
    ADDRESS_IS_DEFAULT(0,"非默认"),
    ADDRESS_NOT_DEFAULT(1,"默认")
    ;




    private final int code;
    private final String msg;

    CommonCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
