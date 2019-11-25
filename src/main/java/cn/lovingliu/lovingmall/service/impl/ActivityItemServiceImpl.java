package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.dto.ActivityItemDTO;
import cn.lovingliu.lovingmall.enums.ExceptionCodeEnum;
import cn.lovingliu.lovingmall.exception.LovingMallException;
import cn.lovingliu.lovingmall.mbg.mapper.ActivityItemMapper;
import cn.lovingliu.lovingmall.mbg.mapper.ActivityMapper;
import cn.lovingliu.lovingmall.mbg.model.Activity;
import cn.lovingliu.lovingmall.mbg.model.ActivityItem;
import cn.lovingliu.lovingmall.mbg.model.GoodsInfo;
import cn.lovingliu.lovingmall.service.ActivityItemService;
import cn.lovingliu.lovingmall.service.GoodsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-13
 */
@Service
@Slf4j
public class ActivityItemServiceImpl implements ActivityItemService {
    @Autowired
    private ActivityItemMapper activityItemMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private GoodsInfoService goodsInfoService;

    public int save(List<ActivityItemDTO> activityItemDTOList){
        List<ActivityItem> activityItemList = new ArrayList<>();
        List<Long> goodsInfoIdList = activityItemDTOList.stream().map(e -> e.getGoodsId()).collect(Collectors.toList());
        List<GoodsInfo> goodsInfoList = goodsInfoService.ListByGoodsIdList(goodsInfoIdList);

        for (ActivityItemDTO activityItemDTO: activityItemDTOList) {
            Long activityId = activityItemDTO.getActivityId();
            // 1.活动是否存在
            Activity activity = activityMapper.selectByPrimaryKey(activityId);
            if(activity == null){
                throw new LovingMallException(ExceptionCodeEnum.ACTIVITY_NOT_EXIT);
            }
            // 2.封装activityItem
            for (GoodsInfo goodsInfo: goodsInfoList) {
                /**
                 * 注意: 在JDK1.8 以后 Long类型判断相等 不能使用 ==
                 */
                if(activityItemDTO.getGoodsId().equals(goodsInfo.getGoodsId())){
                    ActivityItem activityItem = new ActivityItem();
                    BeanUtils.copyProperties(goodsInfo,activityItem);
                    activityItem.setActivityId(activityId);
                    activityItemList.add(activityItem);
                }
            }
        }
        log.warn("【数据处理之后的结果】{}",activityItemList);
        return activityItemMapper.insertSelectiveList(activityItemList);
    }

    @Override
    public List<ActivityItem> list() {
        return  activityItemMapper.selectAll();
    }

    @Override
    public int deleteByActivityItemId(Long activityItemId) {

        return activityItemMapper.deleteByPrimaryKey(activityItemId);
    }

    @Override
    public int deleteByActivityId(Long activityId) {
        return activityItemMapper.deleteByActivityId(activityId);
    }
}
