package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.enums.CommonCodeEnum;
import cn.lovingliu.lovingmall.mbg.mapper.GoodsInfoMapper;
import cn.lovingliu.lovingmall.mbg.model.GoodsInfo;
import cn.lovingliu.lovingmall.service.GoodsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 商品的操作
 * @Date：Created in 2019-10-30
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public List<GoodsInfo> listByUser(int pageNum, int pageSize, String orderBy, String orderType, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy(orderBy+" "+orderType);
        List<GoodsInfo> goodsInfoList = goodsInfoMapper.selectAllByKeywordAndSellStatus(keyword, CommonCodeEnum.PRODUCT_STATUS_ON_SELL.getCode());
        return goodsInfoList;
    }

    @Override
    public List<GoodsInfo> listByAdmin(int pageNum, int pageSize, String orderBy, String orderType, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy(orderBy+" "+orderType);
        List<GoodsInfo> goodsInfoList = goodsInfoMapper.selectAllByKeywordAndSellStatus(keyword,null);
        return goodsInfoList;
    }

    @Override
    public List<GoodsInfo> ListByCategory(String CategoryId) {
        return goodsInfoMapper.selectByCategoryId(CategoryId);
    }
}
