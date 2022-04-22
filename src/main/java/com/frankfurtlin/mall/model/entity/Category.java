package com.frankfurtlin.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 目录表
 * </p>
 *
 * @author Frankfurtlin
 * @since 2022-04-22
 */
@ApiModel(value = "Category对象", description = "目录表")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("目录主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("目录名称")
    private String categoryName;

    @ApiModelProperty("目录级别 1-一级目录 2-二级目录")
    private Integer type;

    @ApiModelProperty("父目录id，一级目录父id为0")
    private Integer parentId;

    @ApiModelProperty("同级目录排序")
    private Integer categoryOrder;

    @ApiModelProperty("目录创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("目录更新时间")
    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public Integer getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(Integer categoryOrder) {
        this.categoryOrder = categoryOrder;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + id +
            ", categoryName=" + categoryName +
            ", type=" + type +
            ", parentId=" + parentId +
            ", categoryOrder=" + categoryOrder +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
