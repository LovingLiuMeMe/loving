package cn.lovingliu.lovingmall.service;

import cn.lovingliu.lovingmall.common.ServerResponse;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-10-30
 */
public interface AuthCodeService {
    ServerResponse generateAuthCode(String userName);
    ServerResponse verifyAuthCode(String userName,String authCode);
}
