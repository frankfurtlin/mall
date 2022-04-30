package com.frankfurtlin.mall.service;

import com.frankfurtlin.mall.model.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.frankfurtlin.mall.model.request.CategoryAddReq;
import com.frankfurtlin.mall.model.request.CategoryUpdateReq;

import java.util.List;

/**
 * <p>
 * 目录表 服务类
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 添加商品分类
     * @param categoryAddReq 添加商品请求实体类
     * @return 数据库操作的行数
     */
    List<Category> addCategory(CategoryAddReq categoryAddReq);

    /**
     * 修改商品分类
     * @param categoryUpdateReq 修改商品请求实体类
     * @return 数据库操作的行数
     */
    List<Category> updateCategory(CategoryUpdateReq categoryUpdateReq);

    /**
     * 删除商品分类
     * @param category 删除商品分类实体类
     * @return 数据库操作的行数
     */
    int deleteCategory(Category category);

    /**
     * 查询商品分类
     * @param parentId 查询商品分类父目录id
     * @return 查询的商品分类列表
     */
    List<Category> getCategory(int parentId);
}
