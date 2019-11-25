package cn.lovingliu.lovingmall.controller.portal;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.mbg.model.Activity;
import cn.lovingliu.lovingmall.mbg.model.ActivityItem;
import cn.lovingliu.lovingmall.service.ActivityItemService;
import cn.lovingliu.lovingmall.service.ActivityService;
import cn.lovingliu.lovingmall.vo.ActivityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-14
 */
@Api(tags = "UserActivityController",description = "用户轮播图")
@RestController
@RequestMapping("/userActivity")
public class UserActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityItemService activityItemService;

    @ApiOperation("获取订单的商品列表")
    @GetMapping("/activityList")
    public ServerResponse activityList(){
        List<Activity> activityList = activityService.listByDeleted(null);

        List<ActivityItem> activityItemList = activityItemService.list();

        List<ActivityVO> activityVOList = activityList.stream().map( e -> {
            ActivityVO activityVO = new ActivityVO();
            BeanUtils.copyProperties(e, activityVO);
            return activityVO;
        }).collect(Collectors.toList());

        for (ActivityVO activityVO : activityVOList) {
            Long activityVOId = activityVO.getActivityId();
            List<ActivityItem> activityItemListTemp = new ArrayList<>();

            for(ActivityItem activityItem : activityItemList) {
                Long  activityVOId2= activityItem.getActivityId();
                if(activityVOId == activityVOId2){
                    activityItemListTemp.add(activityItem);
                }
            }
            activityVO.setActivityItemList(activityItemListTemp);
        }
        return ServerResponse.createBySuccess("获得成功",activityVOList);
    }
}
