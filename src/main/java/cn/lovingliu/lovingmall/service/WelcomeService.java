package cn.lovingliu.lovingmall.service;

import cn.lovingliu.lovingmall.mbg.model.Welcome;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-24
 */
public interface WelcomeService {
    List<Welcome> list();
    Integer saveWelcome(Welcome welcome);
    Integer delWelcome(Integer welcomeId);
}
