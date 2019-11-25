package cn.lovingliu.lovingmall.controller.backend;

import cn.lovingliu.lovingmall.common.CommonPage;
import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.enums.CommonCodeEnum;
import cn.lovingliu.lovingmall.enums.ExceptionCodeEnum;
import cn.lovingliu.lovingmall.exception.LovingMallException;
import cn.lovingliu.lovingmall.mbg.model.Order;
import cn.lovingliu.lovingmall.mbg.model.OrderItem;
import cn.lovingliu.lovingmall.service.OrderItemService;
import cn.lovingliu.lovingmall.service.OrderService;
import cn.lovingliu.lovingmall.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 订单管理
 * @Date：Created in 2019-10-30
 */
@Api(tags = "AdminOrderController", description = "订单管理")
@RestController
@RequestMapping("/adminOrder")
@Slf4j
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @ApiOperation("获取订单列表")
    @GetMapping("/list")
    public ServerResponse<CommonPage> list(@ApiParam("当前页码")
                                            @RequestParam(value = "pageNum",defaultValue = "1")
                                            Integer pageNum,

                                            @ApiParam("页面容量")
                                            @RequestParam(value = "pageSize",defaultValue = "10")
                                            Integer pageSize,

                                            @ApiParam("排序条件")
                                            @RequestParam(value = "orderBy",defaultValue = "create_time")
                                            String orderBy,

                                            @ApiParam("排序方式(升序/降序)")
                                            @RequestParam(value = "orderType",defaultValue = "desc")
                                            String orderType,

                                            @ApiParam("指定的用户")
                                            @RequestParam(value = "user_id",required = false)
                                            Long userId){
        List<Order> orderList = orderService.listWithDeletedStatus(pageNum,pageSize,orderBy,orderType,userId,null);
        return ServerResponse.createBySuccess("获取成功", CommonPage.restPage(orderList));
    }

    @ApiOperation("获得订单详情(此接口有重复)")
    @GetMapping("/info")
    public ServerResponse<OrderVO> info(@ApiParam("订单ID")
                                      @RequestParam("orderId")
                                      Long orderId){
        OrderVO orderVO = new OrderVO();
        Order order = orderService.info(orderId);
        BeanUtils.copyProperties(order, orderVO);

        List<OrderItem> orderItemList = orderItemService.listWithOrderId(orderId);
        orderVO.setOrderItemList(orderItemList);
        return ServerResponse.createBySuccess("获取成功",orderVO);
    }

    @ApiOperation("修改订单交易状态")
    @PostMapping("/updateOrderStatus")
    public ServerResponse updateOrderStatus(@ApiParam("订单ID") @RequestParam("orderId") Long orderId,
                                            @ApiParam("订单状态") @RequestParam("orderStatus") Byte orderStatus){
        Order order = orderService.info(orderId);
        Byte oldOrderStatus = order.getOrderStatus();
        if(oldOrderStatus == CommonCodeEnum.ORDER_STATUS_NO_PAY.getCode()){
            return ServerResponse.createByErrorMessage("订单暂未支付! 为保护您的合法权益,暂无法改变订单状态!");
        }
        // 设置订单状态
        order.setOrderStatus(orderStatus);
        // 保存订单
        int count = orderService.update(order);
        if(count > 0){
            return ServerResponse.createBySuccess("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }
    @ApiOperation("删除订单")
    @PostMapping("/delete")
    public ServerResponse delete(@ApiParam("图片名称")
                                 @RequestParam Long[] orderIds){
        if(orderIds.length < 1){
            throw new LovingMallException(ExceptionCodeEnum.PARAM_ERROR);
        }
        int count = orderService.deleteByOrderIds(orderIds);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

}
