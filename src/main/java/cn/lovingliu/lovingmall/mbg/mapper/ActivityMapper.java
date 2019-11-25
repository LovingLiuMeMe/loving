package cn.lovingliu.lovingmall.mbg.mapper;

import cn.lovingliu.lovingmall.mbg.model.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper {
    int deleteByPrimaryKey(Long activityId);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Long activityId);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    List<Activity> selectByIsDeleted(@Param("isDeleted") Byte isDeleted);
}