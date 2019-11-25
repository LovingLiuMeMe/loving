package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.enums.CommonCodeEnum;
import cn.lovingliu.lovingmall.mbg.mapper.UserAddressMapper;
import cn.lovingliu.lovingmall.mbg.model.UserAddress;
import cn.lovingliu.lovingmall.service.UserAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-17
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    public List<UserAddress> listByUserId(Long userId) {
        return userAddressMapper.findWithUserId(userId);
    }

    @Override
    public Integer save(UserAddress userAddress) {
        Long addressId = userAddress.getAddressId();
        int count = 0;
        // 修改其他状态
        int isDefault = userAddress.getIsDefault();
        if(isDefault == 1){
            userAddressMapper.updateIsDeletedByUserId(userAddress.getUserId(), CommonCodeEnum.ADDRESS_IS_DEFAULT.getCode());
        }
        if(addressId == null){
            // 新建
            count = userAddressMapper.insertSelective(userAddress);
        }else {
            UserAddress oldAddress = userAddressMapper.selectByPrimaryKey(addressId);
            BeanUtils.copyProperties(userAddress, oldAddress);
            count = userAddressMapper.updateByPrimaryKeySelective(oldAddress);
        }
        return count;
    }

    @Override
    public Integer deleteById(Long addressId) {
        return userAddressMapper.deleteByPrimaryKey(addressId);
    }

    @Override
    public int addNewUserAddress(UserAddress userAddress) {
        return userAddressMapper.insertSelective(userAddress);
    }
}
