package cn.lovingliu.lovingmall.component;

import cn.lovingliu.lovingmall.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author：LovingLiu
 * @Description: 订单超时取消并解锁库存的定时器
 * @Date：Created in 2019-11-07
 */
@Component
@Slf4j
public class OrderTimeOutCancelTask {
    @Autowired
    private OrderService orderService;

    @Scheduled(cron = "0/10 0 * * * ?")
    private void cancelTimeOutOrder() {
        orderService.cancelOrderWithTimeOut();
        log.warn("将订单修改为取消的状态,同时将库存归还");
    }
}
