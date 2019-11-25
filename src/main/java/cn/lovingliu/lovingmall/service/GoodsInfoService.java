package cn.lovingliu.lovingmall.service;

import cn.lovingliu.lovingmall.mbg.model.GoodsInfo;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-10-30
 */
public interface GoodsService {
    List<GoodsInfo> listByUser(int pageNum,int pageSize,String orderBy,String orderType,String keyword);
    List<GoodsInfo> listByAdmin(int pageNum,int pageSize,String orderBy,String orderType,String keyword);
    List<GoodsInfo> ListByCategory(String CategoryId);
}
