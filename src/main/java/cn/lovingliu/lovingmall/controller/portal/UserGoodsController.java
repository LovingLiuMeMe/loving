package cn.lovingliu.lovingmall.controller.portal;

import cn.lovingliu.lovingmall.common.CommonPage;
import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.enums.CommonCodeEnum;
import cn.lovingliu.lovingmall.enums.ExceptionCodeEnum;
import cn.lovingliu.lovingmall.exception.LovingMallException;
import cn.lovingliu.lovingmall.mbg.model.GoodsCategory;
import cn.lovingliu.lovingmall.mbg.model.GoodsInfo;
import cn.lovingliu.lovingmall.service.GoodsCategoryService;
import cn.lovingliu.lovingmall.service.GoodsInfoService;
import cn.lovingliu.lovingmall.vo.GoodsCategoryVO;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author：LovingLiu
 * @Description: 买家的商品
 * @Date：Created in 2019-10-30
 */
@Api(tags = "UserGoodsController",description = "买家商品接口")
@RestController
@RequestMapping("/userGoods")
public class UserGoodsController {
    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @ApiOperation("查询商品列表(可带条件)")
    @GetMapping("/list")
    public ServerResponse<CommonPage<GoodsInfo>> list(
                                             @ApiParam("当前页码")
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
        List<GoodsInfo> goodsInfoList = goodsInfoService.listByUser(pageNum,pageSize,orderBy,orderType,keyword);
        return ServerResponse.createBySuccess(CommonPage.restPage(goodsInfoList));
    }

    @ApiOperation("查询分类")
    @GetMapping("/category")
    public ServerResponse<List<GoodsCategoryVO>> category(){
        List<GoodsInfo> goodsInfoList = goodsInfoService.listByGoodsSellStatus(CommonCodeEnum.PRODUCT_STATUS_ON_SELL.getCode());
        List<Long> categoryIdList = goodsInfoList.stream().map(e -> e.getGoodsCategoryId()).collect(Collectors.toList());

        List<GoodsCategory> goodsCategoryList = goodsCategoryService.findInIdListAndDeletedStatus(categoryIdList,CommonCodeEnum.PRODUCT_CATEGORY_NO_DELETED.getCode());

        List<GoodsCategoryVO> goodsCategoryVOList = Lists.newArrayList();

        for (GoodsCategory goodsCategory: goodsCategoryList) {
            Long categoryId = goodsCategory.getCategoryId();
            List<GoodsInfo> categoryGoodsInfoList = new ArrayList<>();
            for(GoodsInfo goodsInfo : goodsInfoList){
                if(categoryId == goodsInfo.getGoodsCategoryId()){
                    categoryGoodsInfoList.add(goodsInfo);
                }
            }
            GoodsCategoryVO goodsCategoryVO = new GoodsCategoryVO();
            BeanUtils.copyProperties(goodsCategory, goodsCategoryVO);
            goodsCategoryVO.setGoodsInfoList(categoryGoodsInfoList);

            goodsCategoryVOList.add(goodsCategoryVO);
        }

        return ServerResponse.createBySuccess(goodsCategoryVOList);
    }

    @ApiOperation("查询商品的详情")
    @GetMapping("/search/{goodsId}")
    public ServerResponse search(@PathVariable("goodsId") Long goodsId){
        GoodsInfo goodsInfo = goodsInfoService.findByGoodsId(goodsId);
        if(goodsInfo == null){
            throw new LovingMallException(ExceptionCodeEnum.PRODUCT_NOT_EXIT);
        }
        return ServerResponse.createBySuccess(goodsInfo);
    }

}
