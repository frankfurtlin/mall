package com.frankfurtlin.mall.controller;


import com.frankfurtlin.mall.common.ApiRestResponse;
import com.frankfurtlin.mall.common.Constant;
import com.frankfurtlin.mall.exception.MallException;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import com.frankfurtlin.mall.model.entity.User;
import com.frankfurtlin.mall.model.request.UserUpdateReq;
import com.frankfurtlin.mall.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/mall/user")
public class UserController {
    @Autowired
    IUserService iUserService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ApiRestResponse<?> register(@ApiParam("用户名") @RequestParam String username, @ApiParam("用户密码") @RequestParam String password){
        if(StringUtils.isEmpty(username)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if(StringUtils.isEmpty(password)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        if(password.length() < Constant.LEAST_PASSWORD_LENGTH){
            return ApiRestResponse.error(MallExceptionEnum.PASSWORD_TOO_SHORT);
        }

        iUserService.register(username, password);
        return ApiRestResponse.success();
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ApiRestResponse<?> login(@ApiParam("用户名") @RequestParam String username, @ApiParam("用户密码") @RequestParam String password, HttpSession httpSession){
        if(StringUtils.isEmpty(username)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if(StringUtils.isEmpty(password)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }

        User user = iUserService.login(username, password);
        user.setPassword(null);
        httpSession.setAttribute(Constant.MALL_USER, user);
        return ApiRestResponse.success(user);
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    public ApiRestResponse<?> update(@ApiParam("用户修改的个人信息") @RequestBody UserUpdateReq userUpdateReq, HttpSession httpSession){
        User user1 = (User)httpSession.getAttribute(Constant.MALL_USER);
        if(user1 == null){
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }

        User user = new User();
        BeanUtils.copyProperties(userUpdateReq, user);

        user.setId(user1.getId());
        if(!iUserService.updateById(user)){
            return ApiRestResponse.error(MallExceptionEnum.DATABASE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("修改用户密码")
    @PostMapping("/password")
    public ApiRestResponse<?> changePassword(@ApiParam("旧密码") @RequestParam String password, @ApiParam("新密码") @RequestParam String newPassword, HttpSession httpSession){
        User user = (User)httpSession.getAttribute(Constant.MALL_USER);
        if(user == null){
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }

        user = iUserService.getById(user.getId());
        iUserService.changePassword(user, password, newPassword);
        return ApiRestResponse.success();
    }

    @ApiOperation("用户退出登录")
    @PostMapping("/logout")
    public ApiRestResponse<?> logout(HttpSession httpSession){
        httpSession.removeAttribute(Constant.MALL_USER);
        return ApiRestResponse.success();
    }

    @ApiOperation("管理员登录")
    @PostMapping("/backLogin")
    public ApiRestResponse<?> backLogin(@ApiParam("管理员账号") @RequestParam String username, @ApiParam("管理员密码") @RequestParam String password, HttpSession httpSession){
        if(StringUtils.isEmpty(username)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if(StringUtils.isEmpty(password)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }

        User user = iUserService.login(username, password);
        if(iUserService.checkNotAdminRole(user)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }

        user.setPassword(null);
        httpSession.setAttribute(Constant.MALL_USER, user);
        return ApiRestResponse.success(user);
    }
}
