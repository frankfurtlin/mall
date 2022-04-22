package com.frankfurtlin.mall.service.impl;

import com.frankfurtlin.mall.model.entity.User;
import com.frankfurtlin.mall.mapper.UserMapper;
import com.frankfurtlin.mall.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
