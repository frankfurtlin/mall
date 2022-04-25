package com.frankfurtlin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.frankfurtlin.mall.common.Constant;
import com.frankfurtlin.mall.exception.MallException;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import com.frankfurtlin.mall.model.entity.User;
import com.frankfurtlin.mall.mapper.UserMapper;
import com.frankfurtlin.mall.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frankfurtlin.mall.utils.Md5Utils;
import com.sun.corba.se.impl.encoding.WrapperInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(String username, String password) throws MallException{
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userMapper.selectOne(userQueryWrapper);
        if(user != null){
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }

        User user1 = new User();
        user1.setUsername(username);
        try {
            user1.setPassword(Md5Utils.getMd5String(password));
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        int k = userMapper.insert(user1);
        if(k != 1){
            throw new MallException(MallExceptionEnum.DATABASE_FAILED);
        }
    }

    @Override
    public User login(String username, String password) throws MallException{
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        try {
            userQueryWrapper.eq("username", username).eq("password", Md5Utils.getMd5String(password));
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        User user = userMapper.selectOne(userQueryWrapper);
        if(user == null){
            throw new MallException(MallExceptionEnum.USERNAME_PASSWORD_ERROR);
        }
        return user;
    }

    @Override
    public boolean checkAdminRole(User user){
        return user.getRole() == Constant.ROLE;
    }
}
