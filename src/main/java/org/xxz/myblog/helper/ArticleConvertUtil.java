package org.xxz.myblog.helper;

import org.xxz.myblog.common.util.DozerUtil;
import org.xxz.myblog.entity.Article;
import org.xxz.myblog.model.dto.ArticleDTO;
import org.xxz.myblog.model.vo.ArticleVO;

import java.util.Date;

/**
 * @author tt
 */
public final class ArticleConvertUtil {

    public static Article articleDTO2Article(ArticleDTO articleDTO, Article article) {
        if(article == null) {
            article = new Article();
        }
        if(articleDTO == null) {
            return article;
        }
        DozerUtil.map(articleDTO, article);
        if (articleDTO.getCreateTime() != null) {
            article.setCreateTime(articleDTO.getCreateTime().getTime());
        }
        return article;
    }

    public static ArticleVO article2ArticleVO(Article article) {
        ArticleVO vo = new ArticleVO();
        if(article == null) {
            return vo;
        }
        DozerUtil.map(article, vo);
        if (article.getCreateTime() != null) {
            vo.setCreateTime(new Date(article.getCreateTime()));
        }
        return vo;
    }

}
