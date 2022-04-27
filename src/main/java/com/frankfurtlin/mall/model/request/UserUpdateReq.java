package com.frankfurtlin.mall.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/4/26 10:26
 * 修改用户信息请求对象
 */
@Data
@ApiModel(value = "UserUpdateReq对象", description = "修改用户信息请求对象")
public class UserUpdateReq {

    @Size(min = 11, max = 11, message = "错误手机号")
    @ApiModelProperty("手机号")
    private String phone;

    @Email(message = "错误邮箱")
    @ApiModelProperty("邮箱")
    private String email;

    @Size(max = 50, message = "个性签名长度不能超过50")
    @ApiModelProperty("个性签名")
    private String signature;

    @ApiModelProperty("用户头像")
    private String avatar;
}
