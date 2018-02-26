package org.xxz.myblog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.xxz.myblog.common.Result;
import org.xxz.myblog.entity.Comment;
import org.xxz.myblog.enums.StatusEnum;
import org.xxz.myblog.helper.CommentConvertUtil;
import org.xxz.myblog.mapper.CommentMapper;
import org.xxz.myblog.model.dto.CommentDTO;
import org.xxz.myblog.model.query.CommentQuery;
import org.xxz.myblog.model.vo.CommentVO;
import org.xxz.myblog.service.CommentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tt
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<CommentVO> getCommentsByArticleId(CommentQuery commentQuery) {
        PageHelper.startPage(commentQuery.getPageNum(), commentQuery.getPageSize());
        List<Comment> list = commentMapper.findByCommentObj(commentQuery.getArticleId());
        Page<Comment> temp = (Page<Comment>) list;
        Page<CommentVO> page = new Page<>(temp.getPageNum(), temp.getPageSize());
        page.setTotal(temp.getTotal());
        for (Comment c : list) {
            page.add(CommentConvertUtil.comment2CommentVO(c, null));
        }
        return page;
    }

    @Override
    public Result<?> save(CommentDTO commentDTO) {
        Assert.notNull(commentDTO, "commentDTO must not be null");
        Comment comment = CommentConvertUtil.commentDTO2Comment(commentDTO, null);
        if (comment.getParentId() != null) {
            Comment parent = commentMapper.findById(comment.getParentId());
            if (parent != null) {
                comment.setToNickName(parent.getNickName());
            }
        }
        comment.setStatus(StatusEnum.OK.getKey());
        comment.setCreateTime(System.currentTimeMillis());
        int row = commentMapper.save(comment);
        return row > 0 ? Result.success("保存成功") : Result.fail("保存失败");
    }

}
