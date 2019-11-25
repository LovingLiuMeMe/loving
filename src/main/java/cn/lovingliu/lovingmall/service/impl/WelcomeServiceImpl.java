package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.mbg.mapper.WelcomeMapper;
import cn.lovingliu.lovingmall.mbg.model.Welcome;
import cn.lovingliu.lovingmall.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-24
 */
@Service
public class WelcomeServiceImpl implements WelcomeService {
    @Autowired
    private WelcomeMapper welcomeMapper;

    @Override
    public List<Welcome> list() {
        return welcomeMapper.list();
    }

    @Override
    public Integer saveWelcome(Welcome welcome) {
        int count = welcomeMapper.insertSelective(welcome);
        return welcome.getWelcomeId();
    }

    @Override
    public Integer delWelcome(Integer welcomeId) {
        return welcomeMapper.deleteByPrimaryKey(welcomeId);
    }
}
