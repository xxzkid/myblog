package org.xxz.myblog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.xxz.myblog.entity.Category;

import java.util.List;

/**
 * @author tt
 */
@Mapper
public interface CategoryMapper {
    
    int save(Category e);
    
    int delete(Long id);
    
    int update(Category e);

    Category findById(Long id);
    
    List<Category> findAll();

    List<Category> findByUserId(Long userId);
}
