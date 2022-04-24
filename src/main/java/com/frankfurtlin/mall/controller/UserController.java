package com.frankfurtlin.mall.controller;


import com.frankfurtlin.mall.common.ApiRestResponse;
import com.frankfurtlin.mall.exception.MallException;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import com.frankfurtlin.mall.model.entity.User;
import com.frankfurtlin.mall.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/test/{id}")
    public User getOne(@ApiParam("用户id") @PathVariable String id){
        return iUserService.getById(id);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ApiRestResponse<?> register(@ApiParam("用户名") @RequestParam String username, @ApiParam("用户密码") @RequestParam String password) throws MallException {
        if(StringUtils.isEmpty(username)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if(StringUtils.isEmpty(password)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        if(password.length() < 8){
            return ApiRestResponse.error(MallExceptionEnum.PASSWORD_TOO_SHORT);
        }

        iUserService.register(username, password);
        return ApiRestResponse.success();
    }
}
