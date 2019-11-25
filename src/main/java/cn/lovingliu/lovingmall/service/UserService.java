package cn.lovingliu.lovingmall.service;

import cn.lovingliu.lovingmall.dto.UserDTO;
import cn.lovingliu.lovingmall.mbg.model.User;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 用户Service
 * @Date：Created in 2019-10-30
 */
public interface UserService {
    int register(UserDTO userDTO);
    User findByUserNameAndPassword(UserDTO userDTO);
    int save(User user);
    List<User> list();
}
