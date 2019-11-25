package cn.lovingliu.lovingmall.service;

import cn.lovingliu.lovingmall.mbg.model.GoodsCategory;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-10-30
 */
public interface GoodsCategoryService {
    List<GoodsCategory> findInIdListAndDeletedStatus(List<Long> categoryIdList,Integer deletedStatus);
    List<GoodsCategory> findAllByDeletedStatus(Integer deletedStatus);
}
