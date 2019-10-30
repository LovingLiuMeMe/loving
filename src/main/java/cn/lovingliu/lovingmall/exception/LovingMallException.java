package cn.lovingliu.lovingmall.exception;

import cn.lovingliu.lovingmall.enums.ExceptionCodeEnum;
import lombok.Data;

/**
 * @Author：LovingLiu
 * @Description: LovingMallException 统一异常处理
 * @Date：Created in 2019-10-29
 */
@Data
public class LovingMallException extends RuntimeException {
    private Integer code;

    public LovingMallException( Integer code, String message) {
        super(message);
        this.code = code;
    }

    public LovingMallException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMsg());
        this.code = exceptionCodeEnum.getCode();
    }
}
