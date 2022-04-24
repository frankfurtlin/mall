package com.frankfurtlin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.frankfurtlin.mall.exception.MallException;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import com.frankfurtlin.mall.model.entity.User;
import com.frankfurtlin.mall.mapper.UserMapper;
import com.frankfurtlin.mall.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.corba.se.impl.encoding.WrapperInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        user1.setPassword(password);
        int k = userMapper.insert(user1);
        if(k != 1){
            throw new MallException(MallExceptionEnum.INSERT_FAILED);
        }
    }
}
