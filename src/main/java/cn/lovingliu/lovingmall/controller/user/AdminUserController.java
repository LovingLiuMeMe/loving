package cn.lovingliu.lovingmall.controller.user;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.dto.AdminUserDTO;
import cn.lovingliu.lovingmall.enums.ExceptionCodeEnum;
import cn.lovingliu.lovingmall.exception.LovingMallException;
import cn.lovingliu.lovingmall.mbg.model.AdminUser;
import cn.lovingliu.lovingmall.service.AdminUserService;
import cn.lovingliu.lovingmall.service.RedisService;
import cn.lovingliu.lovingmall.service.UserAddressService;
import cn.lovingliu.lovingmall.util.CookieUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-21
 */
@Api(tags = "AdminUserController",description = "管理端用户管理")
@RestController
@RequestMapping("/adminUser")
@Slf4j
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserAddressService userAddressService;

    @Value("${redis.key.prefix.loginToken}")
    private String REDIS_LOGIN_TOKEN;

    @Value("${adminUser.cookieKey}")
    private String COOKIE_KEY;

    @Value("${redis.key.expire.loginTokenExpire}")
    private Integer LOGIN_TOKEN_EXPIRE;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ServerResponse register(@ApiParam("注册用户信息") @Valid @RequestBody AdminUserDTO adminUserDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String msg = bindingResult.getFieldError().getDefaultMessage();
            log.error("管理员用户创建时报错:{}",msg);
            throw new LovingMallException(ExceptionCodeEnum.PARAM_ERROR.getCode(),msg);
        }
        int count = adminUserService.register(adminUserDTO);
        if (count > 0){
            return ServerResponse.createBySuccessMessage("创建成功");
        }else {
            return ServerResponse.createByErrorMessage("创建成功");
        }
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ServerResponse login(@RequestBody @Valid AdminUserDTO AdminUserDTO,BindingResult bindingResult,
                                HttpServletResponse response){
        if(bindingResult.hasErrors()){
            String msg = bindingResult.getFieldError().getDefaultMessage();
            log.error("用户登录时报错:{}",msg);
            throw new LovingMallException(ExceptionCodeEnum.PARAM_ERROR.getCode(),msg);
        }
        AdminUser adminUser = adminUserService.findByUserNameAndPassword(AdminUserDTO);
        if(adminUser == null){
            throw new LovingMallException(ExceptionCodeEnum.USER_NOT_EXIT);
        }
        // 1.设置redis
        String cvalue = UUID.randomUUID().toString();
        String rky = String.format(REDIS_LOGIN_TOKEN,cvalue);
        String rvalue = UUID.randomUUID().toString();

        redisService.setValue(rky,rvalue);
        redisService.setExpire(rky,LOGIN_TOKEN_EXPIRE);

        // 2.设置cookie
        CookieUtil.set(response,COOKIE_KEY,cvalue,LOGIN_TOKEN_EXPIRE);
        adminUser.setLoginPassword("");
        return ServerResponse.createBySuccess("登录成功",adminUser);
    }

    @ApiOperation("用户退出")
    @GetMapping("/logout")
    public ServerResponse logout(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Map<String,Object> map){
        Cookie cookie = CookieUtil.get(request,COOKIE_KEY);
        if(cookie == null){
            throw new LovingMallException(ExceptionCodeEnum.LOGIN_TIME_OUT);
        }
        String cvalue = cookie.getValue();
        // 1.清除redis
        String rkey = String.format(REDIS_LOGIN_TOKEN,cvalue);
        redisService.remove(rkey);

        // 2.清除cookie
        CookieUtil.set(response,COOKIE_KEY,null,0);
        return ServerResponse.createBySuccessMessage("退出登录成功");
    }

    @ApiOperation("保存用户信息")
    @PostMapping("/save")
    public ServerResponse save(@ApiParam("用户详情") @RequestBody AdminUser adminUser){
        if(adminUser.getAdminUserId() == null){
            throw new LovingMallException(ExceptionCodeEnum.PARAM_ERROR);
        }
        int count = adminUserService.save(adminUser);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("更新成功");
        }else{
            return ServerResponse.createByErrorMessage("更新失败");
        }
    }
}
