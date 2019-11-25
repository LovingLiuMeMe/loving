package cn.lovingliu.lovingmall.mbg.mapper;

import cn.lovingliu.lovingmall.mbg.model.ActivityItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityItemMapper {
    int deleteByPrimaryKey(Long activityItemId);

    int insert(ActivityItem record);

    int insertSelective(ActivityItem record);

    ActivityItem selectByPrimaryKey(Long activityItemId);

    int updateByPrimaryKeySelective(ActivityItem record);

    int updateByPrimaryKey(ActivityItem record);

    List<ActivityItem> selectAll();
    int insertSelectiveList(@Param("activityItemList") List<ActivityItem> activityItemList);

    int deleteByActivityId(@Param("activityId") Long activityId);
}