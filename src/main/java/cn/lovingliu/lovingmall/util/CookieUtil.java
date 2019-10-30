package cn.lovingliu.lovingmall.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author：LovingLiu
 * @Description: Cookie工具类
 * @Date：Created in 2019-10-30
 */
public class CookieUtil {
    public static void set(HttpServletResponse response,
                           String key,
                           String value,
                           int maxAge){
        Cookie cookie = new Cookie(key,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    public static Cookie get(HttpServletRequest request,String key){
        Map<String,Cookie> cookieMap =readCookieMap(request);
        if(cookieMap.containsKey(key)){
            return cookieMap.get(key);
        }
        return null;
    }
    private static Map<String,Cookie> readCookieMap(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Map<String,Cookie> cookieMap = new HashMap<>();
        for (Cookie cookie:cookies) {
            String name = cookie.getName();
            cookieMap.put(name,cookie);
        }
        return cookieMap;
    }
}
