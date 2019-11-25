package cn.lovingliu.lovingmall.controller.backend;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.mbg.model.Carousel;
import cn.lovingliu.lovingmall.mbg.model.Welcome;
import cn.lovingliu.lovingmall.service.BannerService;
import cn.lovingliu.lovingmall.service.WelcomeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Author：LovingLiu
 * @Description: 客户端基础管路
 * @Date：Created in 2019-11-24
 */
@Api(tags = "AdminIndexController",description = "主页管理")
@RestController
@RequestMapping("/adminIndex")
@Slf4j
public class AdminIndexController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private WelcomeService welcomeService;

    @Value("${ftp.image.prefix}")
    private String imagePrefix;

    @PostMapping("/saveBanner")
    public ServerResponse saveBanner(@RequestBody Carousel carousel) {
        carousel.setCarouselUrl(imagePrefix + carousel.getCarouselUrl());
        Integer carouselId = bannerService.saveBanner(carousel);
        return ServerResponse.createBySuccess("创建成功",carouselId);
    }
    @PostMapping("/delBanner")
    public ServerResponse delBanner(@RequestParam("id") Integer id) {
        int count = bannerService.delBanner(id);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }else {
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }
    @PostMapping("/saveWelcome")
    public ServerResponse saveWelcome(@RequestBody Welcome welcome) {
        welcome.setWelcomeUrl(imagePrefix + welcome.getWelcomeUrl());
        Integer welcomeId = welcomeService.saveWelcome(welcome);
        return ServerResponse.createBySuccess("创建成功",welcomeId);
    }
    @PostMapping("/delWelcome")
    public ServerResponse delWelcome(@RequestParam("id") Integer id) {
        int count = welcomeService.delWelcome(id);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }else {
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }
}
