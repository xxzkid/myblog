package org.xxz.myblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xxz.myblog.entity.Article;
import org.xxz.myblog.model.query.ArticleQuery;

/**
 * @author tt
 */
@Mapper
public interface ArticleMapper {
    
    int save(Article e);
    
    int delete(Long id);
    
    int update(Article e);
    
    Article findById(Long id);
    
    List<Article> findAll(@Param("q") ArticleQuery articleQuery, @Param("status") int status);

    Article findByFixedLink(String fixedLink);

    int updatePv(Long id);
}
