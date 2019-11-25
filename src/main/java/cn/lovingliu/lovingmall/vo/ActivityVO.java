package cn.lovingliu.lovingmall.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-12
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomeActivityVO implements Serializable {
    private static final long serialVersionUID = -7402706407675934300L;


}
