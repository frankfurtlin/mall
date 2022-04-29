package com.frankfurtlin.mall.service;

import com.frankfurtlin.mall.model.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 获取一级目录
     * @return 一级目录列表
     */
    List<Category> getBaseCategory();
}
