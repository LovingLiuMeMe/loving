package cn.lovingliu.lovingmall.service;

import cn.lovingliu.lovingmall.mbg.model.GoodsCategory;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-04
 */
public interface CategoryService {
    List<GoodsCategory> listByDeletedStatus(Integer deletedStatus);
}
