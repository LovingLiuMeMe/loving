package cn.lovingliu.lovingmall.controller.backend;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.enums.CommonCodeEnum;
import cn.lovingliu.lovingmall.mbg.model.GoodsCategory;
import cn.lovingliu.lovingmall.service.GoodsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 分类管理
 * @Date：Created in 2019-11-04
 */
@Api(tags = "AdminCategoryController",description = "分类管理")
@RestController
@RequestMapping("/adminCategory")
@Slf4j
public class AdminCategoryController {
    @Autowired
    private GoodsCategoryService categoryService;

    @GetMapping("/list")
    @ApiOperation("查询分类列表")
    public ServerResponse<List<GoodsCategory>> list(){
        List<GoodsCategory> list = categoryService.findAllByDeletedStatus(CommonCodeEnum.PRODUCT_CATEGORY_NO_DELETED.getCode());
        return ServerResponse.createBySuccess(list);
    }

}
