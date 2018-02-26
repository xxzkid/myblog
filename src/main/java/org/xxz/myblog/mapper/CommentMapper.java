package org.xxz.myblog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.xxz.myblog.entity.Comment;

import java.util.List;

/**
 * @author tt
 */
@Mapper
public interface CommentMapper {

    int save(Comment e);

    int delete(Long id);

    int update(Comment e);

    Comment findById(Long id);

    Comment findByParentId(Long parentId);

    List<Comment> findByCommentObj(Long commentObj);

}
