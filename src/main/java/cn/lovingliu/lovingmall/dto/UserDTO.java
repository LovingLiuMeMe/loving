package cn.lovingliu.lovingmall.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author：LovingLiu
 * @Description: User的DTO
 * @Date：Created in 2019-10-29
 */
@Data
public class UserDTO {

    @NotNull(message = "邮箱不能为空")
    private String userName;

    @NotNull(message = "密码不能为空")
    private String password;

}
