package com.frankfurtlin.mall.service;

import com.frankfurtlin.mall.exception.MallException;
import com.frankfurtlin.mall.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
public interface IUserService extends IService<User> {
    /**
     * 用户注册
     * @param username  用户名
     * @param password  用户密码
     * @throws MallException 抛出异常（用户名已存在、用户插入失败）
     */
    void register(String username, String password) throws MallException;
}
