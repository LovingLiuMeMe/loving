package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.mbg.mapper.CarouselMapper;
import cn.lovingliu.lovingmall.mbg.model.Carousel;
import cn.lovingliu.lovingmall.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 轮播图
 * @Date：Created in 2019-11-11
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> findBannerByIsDeleted(Integer isDeleted) {
        return carouselMapper.selectByIsDeleted(isDeleted);
    }

    @Override
    public Integer saveBanner(Carousel carousel) {
        int count = carouselMapper.insertSelective(carousel);
        return carousel.getCarouselId();
    }

    @Override
    public Integer delBanner(Integer carouselId) {
        return carouselMapper.deleteByPrimaryKey(carouselId);
    }
}
