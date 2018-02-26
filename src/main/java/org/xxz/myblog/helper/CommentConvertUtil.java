package org.xxz.myblog.helper;

import org.xxz.myblog.common.util.DateUtil;
import org.xxz.myblog.entity.Comment;
import org.xxz.myblog.model.dto.CommentDTO;
import org.xxz.myblog.model.vo.CommentVO;

import java.util.Date;

/**
 * @author tt
 */
public class CommentConvertUtil {


    public static CommentVO comment2CommentVO(Comment c, CommentVO vo) {
        if (vo == null) {
            vo = new CommentVO();
        }
        if (c == null) {
            throw new IllegalArgumentException("comment must not be null");
        }
        vo.setId(c.getId());
        vo.setContent(c.getContent());
        vo.setCreateTime(DateUtil.date2String(new Date(c.getCreateTime()), "yyyy-MM-dd HH:mm"));
        vo.setNickName(c.getNickName());
        vo.setSite(c.getSite());
        vo.setToNickName(c.getToNickName());
        return vo;
    }

    public static Comment commentDTO2Comment(CommentDTO commentDTO, Comment c) {

        if (c == null) {
            c = new Comment();
        }

        if (commentDTO == null) {
            throw new IllegalArgumentException("commentDTO must not be null");
        }
        c.setSite(commentDTO.getSite());
        c.setNickName(commentDTO.getNickName());
        c.setCommentObj(commentDTO.getArticleId());
        c.setContent(commentDTO.getContent());
        c.setEmail(commentDTO.getEmail());
        c.setParentId(commentDTO.getParentId());
        return c;
    }
}
