package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.mbg.mapper.GoodsCategoryMapper;
import cn.lovingliu.lovingmall.mbg.model.GoodsCategory;
import cn.lovingliu.lovingmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-04
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private GoodsCategoryMapper categoryMapper;

    @Override
    public List<GoodsCategory> listByDeletedStatus(Integer deletedStatus) {
        return categoryMapper.selectByDeletedStatus(deletedStatus);
    }
}
