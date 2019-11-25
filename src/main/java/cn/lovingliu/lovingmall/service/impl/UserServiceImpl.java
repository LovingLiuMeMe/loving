package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.dto.UserDTO;
import cn.lovingliu.lovingmall.mbg.mapper.UserMapper;
import cn.lovingliu.lovingmall.mbg.model.User;
import cn.lovingliu.lovingmall.service.UserService;
import cn.lovingliu.lovingmall.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 买家
 * @Date：Created in 2019-10-30
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int register(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        String noMd5Password = user.getPassword();
        user.setPassword(MD5Util.MD5EncodeUtf8(noMd5Password));
        int count = userMapper.insertSelective(user);
        return count;
    }

    @Override
    public User findByUserNameAndPassword(UserDTO userDTO) {
        String md5Password = MD5Util.MD5EncodeUtf8(userDTO.getPassword());
        return  userMapper.selectByUserNameAndPassword(userDTO.getUserName(),md5Password);
    }

    @Override
    public int save(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<User> list() {
        return userMapper.selectAll();
    }
}
