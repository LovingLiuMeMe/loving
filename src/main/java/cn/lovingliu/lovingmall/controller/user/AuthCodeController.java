package cn.lovingliu.lovingmall.controller.user;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.service.AuthCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：LovingLiu
 * @Description: 验证码
 * @Date：Created in 2019-10-29
 */
@Api(tags = "AuthController", description = "验证码管理")
@RestController
@RequestMapping("/auth")
public class AuthCodeController {
    @Autowired
    private AuthCodeService authCodeService;

    @ApiOperation("获得验证码")
    @GetMapping("/getAuthCode")
    public ServerResponse getAuthCode(@ApiParam("用户邮箱") @RequestParam("userName") String userName){
        return authCodeService.generateAuthCode(userName);
    }

    @ApiOperation("验证验证码")
    @GetMapping("/verify")
    public ServerResponse verify(@ApiParam("邮箱地址") @RequestParam("userName") String userName,
                                 @ApiParam("验证码") @RequestParam("authCode") String authCode){
        return authCodeService.verifyAuthCode(userName, authCode);
    }
}
