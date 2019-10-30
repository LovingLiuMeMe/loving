package cn.lovingliu.lovingmall.service;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.dto.UserDTO;
import cn.lovingliu.lovingmall.mbg.model.User;

/**
 * @Author：LovingLiu
 * @Description: 用户Service
 * @Date：Created in 2019-10-30
 */
public interface UserService {
    ServerResponse register(UserDTO userDTO);
    User findByUserNameAndPassword(String userName, String password);
    int save(User user);
}
