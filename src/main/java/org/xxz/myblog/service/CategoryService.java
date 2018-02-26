package org.xxz.myblog.service;

import org.xxz.myblog.common.Result;
import org.xxz.myblog.entity.Category;
import org.xxz.myblog.model.dto.CategoryDTO;

import java.util.List;

/**
 * @author tt
 */
public interface CategoryService {
    
    /**
     * 添加分类
     * @param categoryDTO
     * @return
     */
    Result<?> addCategory(CategoryDTO categoryDTO);

    /**
     * 获取用户分类
     * @param userId
     * @return
     */
    List<Category> getCategorys(Long userId);

}
