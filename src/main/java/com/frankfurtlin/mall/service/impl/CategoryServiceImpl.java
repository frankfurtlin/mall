package com.frankfurtlin.mall.service.impl;

import com.frankfurtlin.mall.model.entity.Category;
import com.frankfurtlin.mall.mapper.CategoryMapper;
import com.frankfurtlin.mall.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 目录表 服务实现类
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
