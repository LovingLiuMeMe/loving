package cn.lovingliu.lovingmall.dto;

import cn.lovingliu.lovingmall.mbg.model.OrderItem;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-18
 */
@Data
public class OrderDTO {
    private Long orderId;

    private String orderNo;

    private Long userId;

    private Integer totalPrice;

    private Byte payStatus;

    private Byte payType;

    private Date payTime;

    private Byte orderStatus;

    private String extraInfo;

    private String userName;

    private String userPhone;

    private String userAddress;

    private Byte isDeleted;

    private Date createTime;

    private Date updateTime;

    private List<OrderItem> orderItemList;
}
