package com.frankfurtlin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.frankfurtlin.mall.exception.MallException;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import com.frankfurtlin.mall.model.entity.Category;
import com.frankfurtlin.mall.mapper.CategoryMapper;
import com.frankfurtlin.mall.model.request.CategoryAddReq;
import com.frankfurtlin.mall.model.request.CategoryUpdateReq;
import com.frankfurtlin.mall.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @CachePut(value = "category", key = "#p0.parentId")
    public List<Category> addCategory(CategoryAddReq categoryAddReq){
        checkCategoryNameExisted(categoryAddReq.getCategoryName());

        Category category = new Category();
        BeanUtils.copyProperties(categoryAddReq, category);

        if(categoryMapper.insert(category) != 1){
            throw new MallException(MallExceptionEnum.DATABASE_FAILED);
        }

        return categoryMapper.selectList(new QueryWrapper<Category>().eq("parent_id", category.getParentId()));
    }

    @Override
    @CachePut(value = "category", key = "#p0.parentId")
    public List<Category> updateCategory(CategoryUpdateReq categoryUpdateReq){
        checkCategoryNameExisted(categoryUpdateReq.getCategoryName());

        Category category = new Category();
        BeanUtils.copyProperties(categoryUpdateReq, category);

        if(categoryMapper.updateById(category) != 1){
            throw new MallException(MallExceptionEnum.DATABASE_FAILED);
        }

        return categoryMapper.selectList(new QueryWrapper<Category>().eq("parent_id", category.getParentId()));
    }

    @Override
    @CacheEvict(value = "category", key = "#p0.parentId")
    public int deleteCategory(Category category){
        return categoryMapper.deleteById(category.getId());
    }

    @Override
    @Cacheable(value = "category", key = "#p0")
    public List<Category> getCategory(int parentId){
        return categoryMapper.selectList(new QueryWrapper<Category>().eq("parent_id", parentId).orderByAsc("category_order"));
    }

    private void checkCategoryNameExisted(String categoryName){
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name", categoryName);
        if (categoryMapper.selectOne(categoryQueryWrapper) != null) {
            throw new MallException(MallExceptionEnum.CATEGORY_NAME_EXISTED);
        }
    }
}
