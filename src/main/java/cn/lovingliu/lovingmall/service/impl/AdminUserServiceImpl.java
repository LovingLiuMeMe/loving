package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.dto.AdminUserDTO;
import cn.lovingliu.lovingmall.mbg.mapper.AdminUserMapper;
import cn.lovingliu.lovingmall.mbg.model.AdminUser;
import cn.lovingliu.lovingmall.service.AdminUserService;
import cn.lovingliu.lovingmall.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：LovingLiu
 * @Description: 管理端客户的请求
 * @Date：Created in 2019-11-21
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Override
    public int register(AdminUserDTO adminUserDTO) {
        AdminUser adminUser = new AdminUser();
        BeanUtils.copyProperties(adminUserDTO, adminUser);
        String noMd5Password = adminUser.getLoginPassword();
        adminUser.setLoginPassword(MD5Util.MD5EncodeUtf8(noMd5Password));

        return adminUserMapper.insertSelective(adminUser);
    }

    @Override
    public AdminUser findByUserNameAndPassword(AdminUserDTO adminUserDTO) {
        String md5Password = MD5Util.MD5EncodeUtf8(adminUserDTO.getLoginPassword());
        return  adminUserMapper.selectByUserNameAndPassword(adminUserDTO.getLoginUserName(),md5Password);
    }

    @Override
    public int save(AdminUser adminUser) {
        return  adminUserMapper.updateByPrimaryKey(adminUser);
    }
}
