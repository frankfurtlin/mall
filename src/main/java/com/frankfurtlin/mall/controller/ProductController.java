package com.frankfurtlin.mall.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.frankfurtlin.mall.common.ApiRestResponse;
import com.frankfurtlin.mall.exception.MallExceptionEnum;
import com.frankfurtlin.mall.model.dto.ProductQueryDto;
import com.frankfurtlin.mall.model.entity.Product;
import com.frankfurtlin.mall.model.request.ProductAddReq;
import com.frankfurtlin.mall.model.request.ProductListAdminReq;
import com.frankfurtlin.mall.model.request.ProductListReq;
import com.frankfurtlin.mall.model.request.ProductUpdateReq;
import com.frankfurtlin.mall.model.response.ProductListRes;
import com.frankfurtlin.mall.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/mall/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @ApiOperation("添加商品")
    @PostMapping("/admin/add")
    public ApiRestResponse<?> add(@ApiParam("添加商品实体类") @Valid @RequestBody ProductAddReq productAddReq) {
        iProductService.addProduct(productAddReq);

        return ApiRestResponse.success();
    }

    @ApiOperation("修改商品")
    @PostMapping("/admin/update")
    public ApiRestResponse<?> update(@ApiParam("更新商品实体类") @Valid @RequestBody ProductUpdateReq productUpdateReq) {
        iProductService.updateProduct(productUpdateReq);

        return ApiRestResponse.success();
    }

    @ApiOperation("删除商品")
    @PostMapping("/admin/delete")
    public ApiRestResponse<?> delete(@ApiParam("商品id") @RequestParam Integer id) {
        iProductService.checkProductIdExisted(id);

        if (!iProductService.removeById(id)) {
            return ApiRestResponse.error(MallExceptionEnum.DATABASE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("批量上下架商品")
    @PostMapping("/admin/batchUpdateSellStatus")
    public ApiRestResponse<?> batchUpdateSellStatus(@ApiParam("商品id数组") @RequestParam Integer[] ids, @ApiParam("商品上下架状态 0-下架 1-上架") @RequestParam Integer status) {
        List<Product> list = new ArrayList<>();

        for (int id : ids){
            Product product = iProductService.checkProductIdExisted(id);
            product.setStatus(status);
            list.add(product);
        }

        if (!iProductService.updateBatchById(list)) {
            return ApiRestResponse.error(MallExceptionEnum.DATABASE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("根据商品目录分页查询商品")
    @PostMapping("/admin/list")
    public ApiRestResponse<?> adminList(@ApiParam("商品查询实体类") @Valid @RequestBody ProductListAdminReq productListAdminReq) {
        ProductQueryDto productQueryDto = new ProductQueryDto();
        BeanUtils.copyProperties(productListAdminReq, productQueryDto);
        productQueryDto.setMinStock(-1);

        return ApiRestResponse.success(iProductService.getPage(productQueryDto));
    }

    @ApiOperation("管理员查询商品详情")
    @PostMapping("/admin/details")
    public ApiRestResponse<?> adminDetails(@ApiParam("商品id") @RequestParam Integer id) {
        Product product = iProductService.checkProductIdExisted(id);

        return ApiRestResponse.success(product);
    }

    @ApiOperation("根据商品目录分页查询商品")
    @PostMapping("/list")
    public ApiRestResponse<?> userList(@ApiParam("商品查询实体类") @Valid @RequestBody ProductListReq productListReq) {

        ProductQueryDto productQueryDto = new ProductQueryDto();
        BeanUtils.copyProperties(productListReq, productQueryDto);
        //设置查询条件：库存量大于0、在上架的商品
        productQueryDto.setMinStock(0);
        productQueryDto.setStatus(1);

        IPage<Product> productPage = iProductService.getPage(productQueryDto);

        //隐去商品状态、库存、商品上架时间、商品修改时间
        for(Product product : productPage.getRecords()){
            product.setStatus(-1);
            product.setStock(-1);
            product.setCreateTime(new Date());
            product.setUpdateTime(new Date());
        }

        return ApiRestResponse.success(productPage);
    }

    @ApiOperation("用户查询商品详情")
    @PostMapping("/details")
    public ApiRestResponse<?> userDetails(@ApiParam("商品id") @RequestParam Integer id) {
        Product product = iProductService.checkProductIdExisted(id);

        // 若查询的商品未上架，返回商品id不存在
        if(product.getStatus() == 0){
            return ApiRestResponse.error(MallExceptionEnum.PRODUCT_ID_NOT_EXISTED);
        }

        ProductListRes productListRes = new ProductListRes();
        BeanUtils.copyProperties(product, productListRes);
        System.out.println(productListRes);

        return ApiRestResponse.success(productListRes);
    }

}
