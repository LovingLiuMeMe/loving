package cn.lovingliu.lovingmall.enums;

/**
 * @Author：LovingLiu
 * @Description: 响应结果
 * @Date：Created in 2019-10-29
 */
public enum ResponseCodeEnum implements CodeEnum{
    REQUEST_SUCCESS(200,"请求成功"),
    REQUEST_FAIL(500,"请求失败");

    private final int code;
    private final String msg;

    ResponseCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return 0;
    }

    public String getMsg() {
        return msg;
    }
}
