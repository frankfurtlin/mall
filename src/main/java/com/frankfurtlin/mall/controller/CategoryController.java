package com.frankfurtlin.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.frankfurtlin.mall.common.ApiRestResponse;
import com.frankfurtlin.mall.common.Constant;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import com.frankfurtlin.mall.model.entity.Category;
import com.frankfurtlin.mall.model.entity.User;
import com.frankfurtlin.mall.model.request.CategoryAddReq;
import com.frankfurtlin.mall.model.request.CategoryUpdateReq;
import com.frankfurtlin.mall.service.ICategoryService;
import com.frankfurtlin.mall.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * <p>
 * 目录表 前端控制器
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Api(tags = "商品分类管理")
@RestController
@RequestMapping("/mall/category")
public class CategoryController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;

    @ApiOperation("添加商品分类")
    @PostMapping("/admin/add")
    public ApiRestResponse<?> add(@ApiParam("添加商品分类实体类") @Valid @RequestBody CategoryAddReq categoryAddReq, HttpSession httpSession){
        if(iUserService.checkNotAdminRole(httpSession)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }

        Category category = new Category();
        BeanUtils.copyProperties(categoryAddReq, category);

        if(!iCategoryService.save(category)){
            return ApiRestResponse.error(MallExceptionEnum.DATABASE_FAILED);
        }

        return ApiRestResponse.success();
    }

    @ApiOperation("修改商品分类")
    @PostMapping("/admin/update")
    public ApiRestResponse<?> update(@ApiParam("修改商品分类实体类") @Valid @RequestBody CategoryUpdateReq categoryUpdateReq, HttpSession httpSession){
        if(iUserService.checkNotAdminRole(httpSession)){
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }

        Category category = new Category();
        BeanUtils.copyProperties(categoryUpdateReq, category);

        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name", category.getCategoryName());
        if(iCategoryService.count(categoryQueryWrapper) != 0){
            return ApiRestResponse.error(MallExceptionEnum.CATEGORY_NAME_EXISTED);
        }

        if(!iCategoryService.updateById(category)){
            return ApiRestResponse.error(MallExceptionEnum.DATABASE_FAILED);
        }

        return ApiRestResponse.success();
    }
}
