package org.xxz.myblog.service;

import java.util.List;

import org.xxz.myblog.model.query.ArticleQuery;
import org.xxz.myblog.model.vo.ArticleVO;

/**
 * @author tt
 */
public interface BlogService {



    /**
     * 查询用户all article
     * @param articleQuery
     * @return
     */
    List<ArticleVO> getUserArticle(ArticleQuery articleQuery);

    /**
     * 根据fixedLink获取article
     * @param fixedLink
     * @return
     */
    ArticleVO getArticle(String fixedLink);

    /**
     * 更新article pv
     * @param articleId
     * @return
     */
    int updateArticlePV(Long articleId);

}
