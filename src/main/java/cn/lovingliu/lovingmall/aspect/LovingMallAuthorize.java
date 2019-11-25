package cn.lovingliu.lovingmall.aspect;

import cn.lovingliu.lovingmall.enums.ExceptionCodeEnum;
import cn.lovingliu.lovingmall.exception.LovingMallException;
import cn.lovingliu.lovingmall.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author：LovingLiu
 * @Description: 权限拦截
 * @Date：Created in 2019-11-25
 */
@Aspect
@Component
@Slf4j
public class LovingMallAuthorize {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${adminUser.cookieKey}")
    private String ADMIN_COOKIE_KEY;

    @Value("${user.cookieKey}")
    private String USER_COOKIE_KEY;

    @Value("${redis.key.prefix.loginToken}")
    private String REDIS_LOGIN_TOKEN;

    @Pointcut("execution(public * cn.lovingliu.lovingmall.controller.backend.*.* (..))")
    public void adminVerify(){}
    @Before("adminVerify()")
    public void doAdminVerify() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // 1.获取cookie
        Cookie cookie = CookieUtil.get(request, ADMIN_COOKIE_KEY);
        if(cookie == null){
            log.error("【管理员登陆校验】Cookie中查不到token");
            throw new LovingMallException(ExceptionCodeEnum.AUTHORIZE_FAIL);
        }
        log.info("【管理员登陆校验】");
        // 2.redis查询
        String tokenValue = stringRedisTemplate.opsForValue().get(String.format(REDIS_LOGIN_TOKEN, cookie.getValue()));
        if(StringUtils.isBlank(tokenValue)){
            log.error("【登陆校验】Redis中查不到token");
            throw new LovingMallException(ExceptionCodeEnum.AUTHORIZE_FAIL);
        }
        log.info("【管理员登陆校验】");
    }

    @Pointcut("execution(public * cn.lovingliu.lovingmall.controller.portal.UserOrderController.*.* (..))"+
            "&& execution(public * cn.lovingliu.lovingmall.controller.portal.UserAddressController.*.* (..))")
    public void userVerify(){}
    @Before("userVerify()")
    public void doUserVerify() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // 1.获取cookie
        Cookie cookie = CookieUtil.get(request, USER_COOKIE_KEY);
        if(cookie == null){
            log.error("【用户登陆校验】Cookie中查不到token");
            throw new LovingMallException(ExceptionCodeEnum.AUTHORIZE_FAIL);
        }
        // 2.redis查询
        String tokenValue = stringRedisTemplate.opsForValue().get(String.format(REDIS_LOGIN_TOKEN, cookie.getValue()));
        if(StringUtils.isBlank(tokenValue)){
            log.error("【用户登陆校验】Redis中查不到token");
            throw new LovingMallException(ExceptionCodeEnum.AUTHORIZE_FAIL);
        }
    }
}
