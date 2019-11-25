package cn.lovingliu.lovingmall.controller.backend;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.dto.ActivityItemDTO;
import cn.lovingliu.lovingmall.enums.ExceptionCodeEnum;
import cn.lovingliu.lovingmall.mbg.model.Activity;
import cn.lovingliu.lovingmall.mbg.model.ActivityItem;
import cn.lovingliu.lovingmall.service.ActivityItemService;
import cn.lovingliu.lovingmall.service.ActivityService;
import cn.lovingliu.lovingmall.vo.ActivityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-12
 */
@Api(tags = "AdminActivityController",description = "主页活动设置")
@RestController
@RequestMapping("/adminActivityController")
@Slf4j
public class AdminActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityItemService activityItemService;

    @ApiOperation("创建活动")
    @PostMapping("/saveActivity")
    public ServerResponse saveActivity(@RequestBody  Activity activity){
        if(StringUtils.isBlank(activity.getActivityName())){
            ServerResponse.createByErrorMessage(ExceptionCodeEnum.PARAM_ERROR.getMsg());
        }
        int count = activityService.save(activity);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("创建成功");
        }
        return  ServerResponse.createByErrorMessage("创建失败");
    }

    @ApiOperation("往活动中添加商品")
    @PostMapping("/addActivityItems")
    public ServerResponse addActivityItem(@Valid @RequestBody List<ActivityItemDTO> activityItemDTOList,
                                          BindingResult result){
        if(result.hasErrors()){
            return ServerResponse.createByErrorMessage(result.getFieldError().getDefaultMessage());
        }
        int count = activityItemService.save(activityItemDTOList);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return  ServerResponse.createByErrorMessage("添加失败");
    }
    @ApiOperation("删除活动商品")
    @GetMapping("/deleteActivityItem")
    public ServerResponse deleteActivityItem(@RequestParam("activityItemId") Long activityItemId){
        int count = activityItemService.deleteByActivityItemId(activityItemId);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return  ServerResponse.createByErrorMessage("删除失败");
    }

    @ApiOperation("删除活动商品")
    @GetMapping("/deleteActivity")
    public ServerResponse deleteActivity(@RequestParam("activityId") Long activityId){
        int count = activityService.deleteByActivityId(activityId);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return  ServerResponse.createByErrorMessage("删除失败");
    }

    @ApiOperation("获取订单的商品列表")
    @GetMapping("/activityList")
    public ServerResponse activityList(){
        /**
         * 1.查询所有的首页活动
        */
        List<Activity> activityList = activityService.listByDeleted(null);

        /**
         * 2.查询所有的活动商品
         */
        List<ActivityItem> activityItemList = activityItemService.list();

        /**
         * 数据封装
         */

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
