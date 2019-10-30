package cn.lovingliu.lovingmall.service.impl;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.dto.UserDTO;
import cn.lovingliu.lovingmall.enums.ExceptionCodeEnum;
import cn.lovingliu.lovingmall.exception.LovingMallException;
import cn.lovingliu.lovingmall.mbg.mapper.UserMapper;
import cn.lovingliu.lovingmall.mbg.model.User;
import cn.lovingliu.lovingmall.service.UserService;
import cn.lovingliu.lovingmall.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ServerResponse register(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        String noMd5Password = user.getPassword();
        user.setPassword(MD5Util.MD5EncodeUtf8(noMd5Password));
        int count = userMapper.insertSelective(user);
        if(count > 0){
            return ServerResponse.createBySuccess("注册成功");
        }else{
            throw new LovingMallException(ExceptionCodeEnum.USER_REGISTER_ERROR);
        }
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        return  userMapper.selectByUserNameAndPassword(userName,md5Password);
    }

    @Override
    public int save(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
