package org.xxz.myblog.service;

import org.xxz.myblog.common.Result;
import org.xxz.myblog.common.security.IUser;
import org.xxz.myblog.model.dto.ArticleDTO;
import org.xxz.myblog.model.query.ArticleQuery;
import org.xxz.myblog.model.vo.ArticleVO;

import java.util.List;

/**
 * @author tt
 */
public interface AdminArticleService {

    /**
     * 保存article
     * @param articleDTO
     * @return
     */
    Result<?> addOrUpdateArticle(ArticleDTO articleDTO);

    /**
     * 编辑article
     * @param articleId
     * @param user 当前登录用户
     * @return
     */
    Result<ArticleVO> editArticle(Long articleId, IUser user);


    /**
     * 删除article
     * @param articleId
     * @param user
     * @return
     */
    Result<?> deleteArticle(Long articleId, IUser user);

    /**
     * 获取用户所有article
     * @param articleQuery
     * @return
     */
    List<ArticleVO> getUserArticleAll(ArticleQuery articleQuery);
}
