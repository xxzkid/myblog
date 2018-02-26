package org.xxz.myblog.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 评论(t_comment)
 * @author tt
 *
 */
@Setter
@Getter
@ToString
public class Comment implements java.io.Serializable {
    
    private Long id;
    private String nickName;
    private String email;
    private String site;
    private String content;
    private Long parentId;
    private Long commentObj;
    private String toNickName;
    private Integer status;
    private Long createTime;
    private Long auditUser;
    private Long auditTime;

}
