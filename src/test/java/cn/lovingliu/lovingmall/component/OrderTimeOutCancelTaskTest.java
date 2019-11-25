package cn.lovingliu.lovingmall.component;

import cn.lovingliu.lovingmall.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderTimeOutCancelTaskTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void testDemo(){
        orderService.cancelOrderWithTimeOut();
    }


}