package cn.lovingliu.lovingmall.mbg.mapper;

import cn.lovingliu.lovingmall.mbg.model.Welcome;

import java.util.List;

public interface WelcomeMapper {
    int deleteByPrimaryKey(Integer welcomeId);

    int insert(Welcome record);

    int insertSelective(Welcome record);

    Welcome selectByPrimaryKey(Integer welcomeId);

    int updateByPrimaryKeySelective(Welcome record);

    int updateByPrimaryKey(Welcome record);

    List<Welcome> list();
}