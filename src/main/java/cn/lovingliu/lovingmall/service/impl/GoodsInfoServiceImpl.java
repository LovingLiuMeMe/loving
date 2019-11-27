package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.component.RedisLock;
import cn.lovingliu.lovingmall.dto.GoodsInfoDTO;
import cn.lovingliu.lovingmall.enums.CommonCodeEnum;
import cn.lovingliu.lovingmall.enums.ExceptionCodeEnum;
import cn.lovingliu.lovingmall.exception.LovingMallException;
import cn.lovingliu.lovingmall.mbg.mapper.GoodsInfoMapper;
import cn.lovingliu.lovingmall.mbg.model.GoodsInfo;
import cn.lovingliu.lovingmall.mbg.model.OrderItem;
import cn.lovingliu.lovingmall.service.GoodsInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 商品的操作
 * @Date：Created in 2019-10-30
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Autowired
    private RedisLock redisLock;

    @Value("${redis.lock.timeout}")
    private Integer TIMEOUT;

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
    public List<GoodsInfo> listByGoodsSellStatus(int sellStatus) {
        return goodsInfoMapper.selectAllByKeywordAndSellStatus(null,sellStatus);
    }

    @Override
    public GoodsInfo findByGoodsId(Long goodsId) {
        GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(goodsId);
        if(goodsInfo == null) {
            throw new LovingMallException(ExceptionCodeEnum.PRODUCT_NOT_EXIT);
        }else {
            return goodsInfo;
        }
    }

    @Override
    public Integer updateGoodsInfoSellStatus(Long goodsId) {
        return goodsInfoMapper.updateSellStatusByPrimaryKey(goodsId);
    }

    @Override
    public Integer saveGoodsInfo(GoodsInfoDTO goodsInfoDTO) {
        Long goodsId = goodsInfoDTO.getGoodsId();
        GoodsInfo goodsInfo = new GoodsInfo();
        int count = 0;
        if(goodsId == null){
            Date now = new Date();
            BeanUtils.copyProperties(goodsInfoDTO, goodsInfo);
            goodsInfo.setCreateTime(now);
            goodsInfo.setUpdateTime(now);
            count = goodsInfoMapper.insertSelective(goodsInfo);
        }else{
            goodsInfo = this.findByGoodsId(goodsInfoDTO.getGoodsId());
            BeanUtils.copyProperties(goodsInfoDTO, goodsInfo);
            goodsInfo.setUpdateTime(new Date());
            count = goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
        }
        return count;
    }

    @Override
    public Integer removeGoodsInfo(List<Long> goodsInfoIdList) {
        if(goodsInfoIdList.size() > 0){
            return goodsInfoMapper.deleteByPrimaryKeyList(goodsInfoIdList);
        }else{
            throw new LovingMallException(ExceptionCodeEnum.PARAM_ERROR);
        }
    }

    @Override
    public List<GoodsInfo> listAllBySellStatus(Integer goodsSellStatus) {
        return goodsInfoMapper.selectAllByKeywordAndSellStatus(null,goodsSellStatus);
    }

    @Override
    public List<GoodsInfo> ListByGoodsIdList(List<Long> goodsIdList) {
        if(goodsIdList.size() > 0){
            return goodsInfoMapper.selectByPrimaryKeyList(goodsIdList);
        }else{
            throw new LovingMallException(ExceptionCodeEnum.PARAM_ERROR);
        }

    }

    @Override
    public Integer decreaseStock(List<OrderItem> orderItemList) {
        /**
         * 加锁
         */
        int count = 0;
        for (OrderItem orderItem: orderItemList) {
            /**
             * 加锁
             */
            long time = System.currentTimeMillis() + TIMEOUT;
            if(!redisLock.lock(orderItem.getGoodsId().toString(), String.valueOf(time))){
                throw new LovingMallException(ExceptionCodeEnum.SYSTEM_BUSY);
            }
            GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(orderItem.getGoodsId());
            if(goodsInfo == null){
                throw new LovingMallException(ExceptionCodeEnum.PRODUCT_NOT_EXIT);
            }
            Integer resultStock = goodsInfo.getStockNum() - orderItem.getGoodsCount();

            if(resultStock < 0){
                throw new LovingMallException(ExceptionCodeEnum.STOCK_WARN);
            }
            goodsInfo.setStockNum(resultStock);
            count += goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
            /**
             * 解锁
             */
            redisLock.unlock(orderItem.getGoodsId().toString(),String.valueOf(time));
        }
        return count;
    }
}
