package com.frankfurtlin.mall.controller;


import com.frankfurtlin.mall.model.entity.User;
import com.frankfurtlin.mall.service.IUserService;
import com.sun.org.apache.bcel.internal.generic.ISUB;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;

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

    @ApiOperation("测试用户")
    @GetMapping("/get")
    public User getOne(){
        return iUserService.getById(1);
    }
}
