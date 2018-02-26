package org.xxz.myblog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xxz.myblog.common.Result;
import org.xxz.myblog.common.exception.CustomRuntimeException;
import org.xxz.myblog.common.security.IUser;
import org.xxz.myblog.entity.Article;
import org.xxz.myblog.entity.Category;
import org.xxz.myblog.enums.ArticleStatusEnum;
import org.xxz.myblog.exception.ArticleException;
import org.xxz.myblog.exception.CategoryException;
import org.xxz.myblog.helper.ArticleConvertUtil;
import org.xxz.myblog.mapper.ArticleMapper;
import org.xxz.myblog.mapper.CategoryMapper;
import org.xxz.myblog.model.dto.ArticleDTO;
import org.xxz.myblog.model.query.ArticleQuery;
import org.xxz.myblog.model.vo.ArticleVO;
import org.xxz.myblog.service.AdminArticleService;
import org.xxz.myblog.util.MarkdownUtil;

import javax.annotation.Resource;
import java.util.List;

import static org.xxz.myblog.common.enums.ErrorEnum.PARAM_ERROR;
import static org.xxz.myblog.enums.ArticleExceptionEnum.*;
import static org.xxz.myblog.enums.CategoryExceptionEnum.NOT_FOUND_CATEGORY;

/**
 * @author tt
 */
@Slf4j
@Service
public class AdminArticleServiceImpl implements AdminArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Resource
    private CategoryMapper categoryMapper;

    @Transactional
    @Override
    public Result<?> addOrUpdateArticle(ArticleDTO articleDTO) {
        if(articleDTO == null) {
            throw new IllegalArgumentException("参数错误");
        }

        String html = MarkdownUtil.markdown2Html(articleDTO.getContent());

        String categoryName = null;
        if (articleDTO.getCategoryId() == null || articleDTO.getCategoryId() == 0) {
            articleDTO.setCategoryId(0L);
            categoryName = "默认";
        }

        if (articleDTO.getCategoryId() != 0) {
            Category category = categoryMapper.findById(articleDTO.getCategoryId());
            if (category == null || category.getCreateUser() != articleDTO.getUpdateUser()) {
                throw new CategoryException(NOT_FOUND_CATEGORY);
            }
            categoryName = category.getName();
        }

        if(articleDTO.getId() == null) {
            // 查询fixedLink是否存在
            Article p = articleMapper.findByFixedLink(articleDTO.getFixedLink());
            if(p != null) {
                throw new ArticleException(FIXED_LINK_EXISTS);
            }

            Article article = ArticleConvertUtil.articleDTO2Article(articleDTO, null);
            long now = System.currentTimeMillis();
            if(article.getCreateTime() == null) {
                article.setCreateTime(now);
            }
            article.setCreateUser(articleDTO.getUpdateUser());
            article.setCreateUserName(articleDTO.getUpdateUserName());
            article.setUpdateTime(now);
            article.setHtml(html);
            article.setCategoryName(categoryName);
            articleMapper.save(article);
        } else {
            Article oldArticle = articleMapper.findById(articleDTO.getId());

            if(!oldArticle.getCreateUser().equals(oldArticle.getCreateUser())) {
                throw new ArticleException(NOT_EDIT_ARTICLE);
            }

            ArticleConvertUtil.articleDTO2Article(articleDTO, oldArticle);
            long now = System.currentTimeMillis();
            oldArticle.setUpdateTime(now);
            oldArticle.setHtml(html);
            oldArticle.setCategoryName(categoryName);
            articleMapper.update(oldArticle);

        }
        return Result.success("保存成功");
    }

    @Override
    public Result<ArticleVO> editArticle(Long articleId, IUser user) {
        if(articleId == null || user == null) {
            log.error("articleId or user is null");
            return Result.fail("参数错误", null);
        }

        Article article = articleMapper.findById(articleId);
        if(article == null) {
            throw new ArticleException(NOT_FOUND_ARTICLE);
        }

        if(!article.getCreateUser().equals(user.getId())) {
            throw new ArticleException(NOT_EDIT_ARTICLE);
        }
        return Result.success("", ArticleConvertUtil.article2ArticleVO(article));
    }

    @Transactional
    @Override
    public Result<?> deleteArticle(Long articleId, IUser user) {
        if(articleId == null && user == null) {
            throw new CustomRuntimeException(PARAM_ERROR);
        }
        Article article = articleMapper.findById(articleId);
        if(article == null) {
            throw new ArticleException(NOT_FOUND_ARTICLE);
        }
        if(!article.getCreateUser().equals(user.getId())) {
            throw new ArticleException(NOT_DELETE_ARTICLE);
        }
        articleMapper.delete(articleId);
        return Result.success("删除成功");
    }

    @Override
    public List<ArticleVO> getUserArticleAll(ArticleQuery articleQuery) {
        PageHelper.startPage(articleQuery.getPageNum(), articleQuery.getPageSize());
        Page<Article> articles = (Page<Article>) articleMapper.findAll(articleQuery, ArticleStatusEnum.ALL.getKey());
        Page<ArticleVO> page = new Page<>(articleQuery.getPageNum(), articleQuery.getPageSize());
        page.setTotal(articles.getTotal());
        for(Article article : articles) {
            page.add(ArticleConvertUtil.article2ArticleVO(article));
        }
        return page;
    }

}
