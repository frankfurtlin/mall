package com.frankfurtlin.mall.controller;

import com.frankfurtlin.mall.common.ApiRestResponse;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import com.frankfurtlin.mall.model.entity.Category;
import com.frankfurtlin.mall.model.request.CategoryAddReq;
import com.frankfurtlin.mall.model.request.CategoryUpdateReq;
import com.frankfurtlin.mall.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
        List<Category> list = iCategoryService.addCategory(categoryAddReq);
        if(list.isEmpty()){
            return ApiRestResponse.error(MallExceptionEnum.DATABASE_FAILED);
        }

        return ApiRestResponse.success();
    }

    @ApiOperation("修改商品分类")
    @PostMapping("/admin/update")
    public ApiRestResponse<?> update(@ApiParam("修改商品分类实体类") @Valid @RequestBody CategoryUpdateReq categoryUpdateReq) {
        List<Category> list = iCategoryService.updateCategory(categoryUpdateReq);
        if(list.isEmpty()){
            return ApiRestResponse.error(MallExceptionEnum.DATABASE_FAILED);
        }

        return ApiRestResponse.success();
    }

    @ApiOperation("删除商品分类")
    @PostMapping("/admin/delete")
    public ApiRestResponse<?> delete(@ApiParam("删除商品分类实体id") @RequestParam int id) {
        Category category = iCategoryService.getById(id);
        if(category == null){
            return ApiRestResponse.error(MallExceptionEnum.CATEGORY_ID_NOT_EXISTED);
        }

        if (iCategoryService.deleteCategory(category) != 1) {
            return ApiRestResponse.error(MallExceptionEnum.DATABASE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("获取商品一级分类")
    @GetMapping("/get")
    public ApiRestResponse<?> get() {
        List<Category> list = iCategoryService.getCategory(0);
        return ApiRestResponse.success(list);
    }

    @ApiOperation("获取商品子分类")
    @PostMapping("/get")
    public ApiRestResponse<?> get(@ApiParam("商品父分类id") @RequestParam int parentId) {
        List<Category> list = iCategoryService.getCategory(parentId);
        return ApiRestResponse.success(list);
    }
}
