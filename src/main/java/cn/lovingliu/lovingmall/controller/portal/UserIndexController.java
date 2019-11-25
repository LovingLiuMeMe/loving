package cn.lovingliu.lovingmall.controller.portal;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.enums.CommonCodeEnum;
import cn.lovingliu.lovingmall.mbg.model.Carousel;
import cn.lovingliu.lovingmall.mbg.model.Welcome;
import cn.lovingliu.lovingmall.service.BannerService;
import cn.lovingliu.lovingmall.service.WelcomeService;
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
@Api(tags = "UserIndexController",description = "用户主页信息")
@RestController
@RequestMapping("/userIndex")
public class UserIndexController {

    @Autowired
    private BannerService bannerService;
    @Autowired
    private WelcomeService welcomeService;

    @GetMapping("/bannerlist")
    public ServerResponse bannerList() {
        List<Carousel> carouselList = bannerService.findBannerByIsDeleted(CommonCodeEnum.BANNER_STATUS_NO_DELETED.getCode());
        return ServerResponse.createBySuccess(carouselList);
    }

    @GetMapping("/welcomeList")
    public ServerResponse welcomeList() {
        List<Welcome> welcomeList = welcomeService.list();
        return ServerResponse.createBySuccess(welcomeList);
    }
}
