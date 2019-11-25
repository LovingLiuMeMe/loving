package cn.lovingliu.lovingmall.service;

import cn.lovingliu.lovingmall.dto.AdminUserDTO;
import cn.lovingliu.lovingmall.mbg.model.AdminUser;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-21
 */
public interface AdminUserService {
    int register(AdminUserDTO adminUserDTO);
    AdminUser findByUserNameAndPassword(AdminUserDTO adminUserDTO);
    int save(AdminUser adminUser);
}
