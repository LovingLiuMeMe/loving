package cn.lovingliu.lovingmall.service;

import cn.lovingliu.lovingmall.dto.ActivityItemDTO;
import cn.lovingliu.lovingmall.mbg.model.ActivityItem;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-13
 */
public interface ActivityItemService {
    int save(List<ActivityItemDTO> activityItemDTOList);
    List<ActivityItem> list();
    int deleteByActivityItemId(Long activityItemId);
    int deleteByActivityId(Long activityId);
}
