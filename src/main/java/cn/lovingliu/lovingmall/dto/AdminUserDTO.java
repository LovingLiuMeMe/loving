package cn.lovingliu.lovingmall.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-21
 */
@Data
public class AdminUserDTO {
    @NotNull(message = "电话号码必填")
    private String loginUserName;
    @NotNull(message = "密码必须填写")
    private String loginPassword;

    private String nickName;
}
