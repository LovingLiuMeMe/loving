package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.mbg.mapper.GoodsCategoryMapper;
import cn.lovingliu.lovingmall.mbg.model.GoodsCategory;
import cn.lovingliu.lovingmall.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-10-30
 */
@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public List<GoodsCategory> findInIdListAndDeletedStatus(List<Long> categoryIdList, Integer deletedStatus) {
        return goodsCategoryMapper.selectInIdsAndIsDeleted(categoryIdList,deletedStatus);
    }

    @Override
    public List<GoodsCategory> findAllByDeletedStatus(Integer deletedStatus) {
        return goodsCategoryMapper.selectByDeletedStatus(deletedStatus);
    }
}
