package cn.lovingliu.lovingmall.handle;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.exception.LovingMallException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author：LovingLiu
 * @Description: 统一异常处理
 * @Date：Created in 2019-10-30
 */
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = LovingMallException.class)
    @ResponseBody
    public ServerResponse resolveException(Exception e){
        if(e instanceof LovingMallException){
            LovingMallException lovingMallException = (LovingMallException) e;
            return ServerResponse.createByErrorCodeMessage(lovingMallException.getCode(),lovingMallException.getMessage());
        }
        return ServerResponse.createByErrorMessage(e.getMessage());
    }
}
