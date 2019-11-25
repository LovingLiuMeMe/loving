package cn.lovingliu.lovingmall.service;

import cn.lovingliu.lovingmall.mbg.model.Carousel;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 轮播图业务处理
 * @Date：Created in 2019-11-11
 */
public interface BannerService {
    List<Carousel> findBannerByIsDeleted(Integer isDeleted);
    Integer saveBanner(Carousel carousel);
    Integer delBanner(Integer carouselId);
}
