package com.frankfurtlin.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.frankfurtlin.mall.common.ApiRestResponse;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import com.frankfurtlin.mall.model.entity.Category;
import com.frankfurtlin.mall.model.request.CategoryAddReq;
import com.frankfurtlin.mall.model.request.CategoryUpdateReq;
import com.frankfurtlin.mall.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    private ICategoryService iCategoryService;

    @ApiOperation("添加商品分类")
    @PostMapping("/admin/add")
    public ApiRestResponse<?> add(@ApiParam("添加商品分类实体类") @Valid @RequestBody CategoryAddReq categoryAddReq) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryAddReq, category);

        if (!iCategoryService.save(category)) {
            return ApiRestResponse.error(MallExceptionEnum.DATABASE_FAILED);
        }

        return ApiRestResponse.success();
    }

    @ApiOperation("修改商品分类")
    @PostMapping("/admin/update")
    public ApiRestResponse<?> update(@ApiParam("修改商品分类实体类") @Valid @RequestBody CategoryUpdateReq categoryUpdateReq) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryUpdateReq, category);

        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name", category.getCategoryName());
        if (iCategoryService.count(categoryQueryWrapper) != 0) {
            return ApiRestResponse.error(MallExceptionEnum.CATEGORY_NAME_EXISTED);
        }

        if (!iCategoryService.updateById(category)) {
            return ApiRestResponse.error(MallExceptionEnum.DATABASE_FAILED);
        }

        return ApiRestResponse.success();
    }

    @ApiOperation("删除商品分类")
    @PostMapping("/admin/delete")
    public ApiRestResponse<?> delete(@ApiParam("删除商品分类实体id") @RequestParam int id) {
        if (iCategoryService.getById(id) == null) {
            return ApiRestResponse.error(MallExceptionEnum.CATEGORY_ID_NOT_EXISTED);
        }

        if (!iCategoryService.removeById(id)) {
            return ApiRestResponse.error(MallExceptionEnum.DATABASE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("获取商品一级分类")
    @GetMapping("/get")
    public ApiRestResponse<?> get() {
        List<Category> list = iCategoryService.getBaseCategory();
        return ApiRestResponse.success(list);
    }

    @ApiOperation("获取商品子分类")
    @PostMapping("/get")
    public ApiRestResponse<?> get(@ApiParam("商品父分类id") @RequestParam int id) {
        List<Category> list = iCategoryService.list(new QueryWrapper<Category>().eq("parent_id", id).orderByAsc("category_order"));
        return ApiRestResponse.success(list);
    }
}
