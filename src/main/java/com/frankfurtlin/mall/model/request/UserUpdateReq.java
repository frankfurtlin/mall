package com.frankfurtlin.mall.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/4/26 10:26
 * 修改用户信息请求对象
 */
@Data
@ApiModel(value = "UserUpdateReq对象", description = "修改用户信息请求对象")
public class UserUpdateReq {

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("个性签名")
    private String signature;

    @ApiModelProperty("用户头像")
    private String avatar;
}
