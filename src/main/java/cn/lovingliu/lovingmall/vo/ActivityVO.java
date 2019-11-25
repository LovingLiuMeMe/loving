package cn.lovingliu.lovingmall.vo;

import cn.lovingliu.lovingmall.mbg.model.ActivityItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-12
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityVO implements Serializable {
    private static final long serialVersionUID = -7402706407675934300L;

    private Long activityId;

    private String activityName;

    private Byte isDeleted;

    List<ActivityItem> activityItemList;
}
