package cn.lovingliu.lovingmall.service;

import cn.lovingliu.lovingmall.dto.GoodsInfoDTO;
import cn.lovingliu.lovingmall.mbg.model.GoodsInfo;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-10-30
 */
public interface GoodsInfoService {
    List<GoodsInfo> listByUser(int pageNum, int pageSize, String orderBy, String orderType, String keyword);
    List<GoodsInfo> listByAdmin(int pageNum,int pageSize,String orderBy,String orderType,String keyword);
    List<GoodsInfo> listByGoodsSellStatus(int sellStatus);
    GoodsInfo findByGoodsId(Long goodsId);
    Integer updateGoodsInfoSellStatus(Long goodsId);
    Integer saveGoodsInfo(GoodsInfoDTO goodsInfoDTO);
    Integer removeGoodsInfo(List<Long> goodsInfoIdList);
    List<GoodsInfo> listAllBySellStatus(Integer goodsSellStatus);
    List<GoodsInfo> ListByGoodsIdList(List<Long> goodsIdList);
}
