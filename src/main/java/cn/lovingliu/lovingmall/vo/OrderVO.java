package cn.lovingliu.lovingmall.vo;

import cn.lovingliu.lovingmall.mbg.model.OrderItem;
import cn.lovingliu.lovingmall.util.serializer.DateToDateFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-05
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderVO {

    private Long orderId;

    private String orderNo;

    private Long userId;

    private Integer totalPrice;

    private Byte payStatus;

    private Byte payType;

    @JsonSerialize(using = DateToDateFormat.class)
    private Date payTime;

    private Byte orderStatus;

    private String extraInfo;

    private String userName;

    private String userPhone;

    private String userAddress;

    private Byte isDeleted;

    @JsonSerialize(using = DateToDateFormat.class)
    private Date createTime;

    @JsonSerialize(using = DateToDateFormat.class)
    private Date updateTime;

    private List<OrderItem> orderItemList;
}
