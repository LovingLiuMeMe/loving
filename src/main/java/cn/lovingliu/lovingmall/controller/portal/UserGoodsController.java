package cn.lovingliu.lovingmall.controller.portal;

import cn.lovingliu.lovingmall.common.CommonPage;
import cn.lovingliu.lovingmall.common.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：LovingLiu
 * @Description: 买家的商品
 * @Date：Created in 2019-10-30
 */
@Api(tags = "UserProductController",description = "买家商品接口")
@RestController
@RequestMapping("/userProduct")
public class UserProductController {
    @Autowired


    @ApiOperation("查询商品列表(可带条件)")
    @GetMapping("/list")
    public ServerResponse<CommonPage<>> list(@ApiParam("当前页码")
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
                                             @ApiParam("查询条件")
                                             @RequestParam(value = "search",required = false)
                                             String keyword){

    }

}
