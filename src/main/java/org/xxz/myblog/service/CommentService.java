package org.xxz.myblog.service;

import org.xxz.myblog.common.Result;
import org.xxz.myblog.model.dto.CommentDTO;
import org.xxz.myblog.model.query.CommentQuery;
import org.xxz.myblog.model.vo.CommentVO;

import java.util.List;

/**
 * @author tt
 */
public interface CommentService {

    /**
     * 根据文章id获取评论
     * @param commentQuery
     * @return
     */
    List<CommentVO> getCommentsByArticleId(CommentQuery commentQuery);

    /**
     * 评论文章
     * @param commentDTO
     * @return
     */
    Result<?> save(CommentDTO commentDTO);
}
