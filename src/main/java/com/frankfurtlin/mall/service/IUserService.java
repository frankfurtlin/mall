package com.frankfurtlin.mall.service;

import com.frankfurtlin.mall.exception.MallException;
import com.frankfurtlin.mall.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;

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
    void register(String username, String password);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     * @throws MallException 抛出异常（用户名或密码错误）
     */
    User login(String username, String password);

    /**
     * 检验用户是否有管理员权限
     * @param user 用户信息
     * @return true-是管理员 false-不是管理员
     */
    boolean checkAdminRole(User user);

    /**
     * 修改用户密码
     * @param user 用户信息
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @throws MallException 旧密码错误异常
     */
    void changePassword(User user, String oldPassword, String newPassword);
}
