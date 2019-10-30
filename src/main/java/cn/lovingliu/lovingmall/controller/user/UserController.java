package cn.lovingliu.lovingmall.controller.user;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.dto.UserDTO;
import cn.lovingliu.lovingmall.enums.ExceptionCodeEnum;
import cn.lovingliu.lovingmall.exception.LovingMallException;
import cn.lovingliu.lovingmall.mbg.model.User;
import cn.lovingliu.lovingmall.service.RedisService;
import cn.lovingliu.lovingmall.service.UserService;
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
 * @Description: 普通用户
 * @Date：Created in 2019-10-29
 */
@Api(tags = "UserController",description = "普通用户管理")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.loginToken}")
    private String REDIS_LOGIN_TOKEN;

    @Value("${user.cookieKey}")
    private String COOKIE_KEY;

    @Value("${redis.key.expire.loginTokenExpire}")
    private Integer LOGIN_TOKEN_EXPIRE;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ServerResponse register(@ApiParam("注册用户信息") @Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String msg = bindingResult.getFieldError().getDefaultMessage();
            log.error("用户注册时报错:{}",msg);
            throw new LovingMallException(ExceptionCodeEnum.PARAM_ERROR.getCode(),msg);
        }

        return userService.register(userDTO);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ServerResponse login(@ApiParam("用户邮箱") @RequestParam("userName") String userName,
                                @ApiParam("用户密码") @RequestParam("password") String password,
                                HttpServletResponse response){
        User user = userService.findByUserNameAndPassword(userName,password);
        if(user == null){
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
        return ServerResponse.createBySuccess(user);
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
        return ServerResponse.createBySuccessMessage("退出登录");
    }

    @ApiOperation("保存用户信息")
    @PostMapping("/save")
    public ServerResponse save(@ApiParam("用户详情") @RequestBody User user){
        if(user.getUserId() == null){
            throw new LovingMallException(ExceptionCodeEnum.PARAM_ERROR);
        }
        int count = userService.save(user);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("更新成功");
        }else{
            return ServerResponse.createByErrorMessage("更新失败");
        }
    }
}
