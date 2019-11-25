package cn.lovingliu.lovingmall.mbg.mapper;

import cn.lovingliu.lovingmall.mbg.model.UserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Long addressId);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Long addressId);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    List<UserAddress> findWithUserId(@Param("userId") Long userId);

    int updateIsDeletedByUserId(@Param("userId") Long userId, @Param("isDefault") Integer isDefault);

}