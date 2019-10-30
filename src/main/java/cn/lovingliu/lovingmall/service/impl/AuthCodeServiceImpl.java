package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.enums.ExceptionCodeEnum;
import cn.lovingliu.lovingmall.exception.LovingMallException;
import cn.lovingliu.lovingmall.service.AuthCodeService;
import cn.lovingliu.lovingmall.service.EmailService;
import cn.lovingliu.lovingmall.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-10-30
 */

@Service
@Slf4j
public class AuthCodeServiceImpl implements AuthCodeService {
    @Autowired
    private RedisService redisService;

    @Autowired
    private EmailService emailService;

    @Value("${redis.key.prefix.authCode}")
    private String AUTH_CODE_PREFIX;

    @Value("${redis.key.expire.authCodeExpire}")
    private Integer AUTH_CODE_EXPIRE;

    @Override
    public ServerResponse generateAuthCode(String userName) {
        String redisKey = String.format(AUTH_CODE_PREFIX,userName);
        if(StringUtils.isBlank(redisService.getValue(redisKey))){
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for(int i = 0; i<6 ;i++){
                sb.append(random.nextInt(10));
            }
            redisService.setValue(redisKey,sb.toString());
            redisService.setExpire(redisKey,AUTH_CODE_EXPIRE);
            // 发送邮件
            emailService.sendSimpleMail(userName,"LovingMall注册",sb.toString());
            return ServerResponse.createBySuccessMessage("验证码发送成功,请前往邮箱查看");
        }else{
            throw new LovingMallException(ExceptionCodeEnum.AUTH_TIMES_LIMIT);
        }
    }

    @Override
    public ServerResponse verifyAuthCode(String userName, String authCode) {
        String redisKey = String.format(AUTH_CODE_PREFIX,userName);
        String realAuthCode = redisService.getValue(redisKey);
        if(StringUtils.isBlank(realAuthCode)){
            log.error("验证码超时");
            throw new LovingMallException(ExceptionCodeEnum.AUTH_CODE_TIME_OUT);
        }
        if(realAuthCode.equals(authCode)){
            log.info("username:{} 验证成功",userName);
            return ServerResponse.createBySuccessMessage("验证成功");
        }
        return ServerResponse.createBySuccessMessage("验证成功");
    }
}
