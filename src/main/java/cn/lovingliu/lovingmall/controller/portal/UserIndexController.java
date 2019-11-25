package cn.lovingliu.lovingmall.controller.portal;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.enums.CommonCodeEnum;
import cn.lovingliu.lovingmall.mbg.model.Carousel;
import cn.lovingliu.lovingmall.service.BannerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 获取商品列表
 * @Date：Created in 2019-11-11
 */
@Api(tags = "UserBannerController",description = "用户轮播图")
@RestController
@RequestMapping("/userBanner")
public class UserBannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/bannerlist")
    public ServerResponse bannerList() {
        List<Carousel> carouselList = bannerService.findBannerByIsDeleted(CommonCodeEnum.BANNER_STATUS_NO_DELETED.getCode());
        return ServerResponse.createBySuccess(carouselList);
    }
}
