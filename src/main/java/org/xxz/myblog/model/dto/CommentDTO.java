package org.xxz.myblog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author tt
 */
@Setter
@Getter
@ToString
public class CommentDTO implements java.io.Serializable {

    @NotNull(message = "评论对象不能为空")
    private Long articleId;
    @NotBlank(message = "评论内容不能为空")
    @Length(max = 200, message = "评论内容长度不能超过200个字")
    private String content;
    @NotBlank(message = "昵称不能为空")
    private String nickName;
    @NotBlank(message = "邮箱不能为空")
    private String email;
    private String site;
    @NotNull(message = "回复评论不能为空")
    private Long parentId;
}
