package org.xxz.myblog.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 文章(t_article)
 * @author tt
 *
 */
@Getter
@Setter
@ToString
public class Article implements java.io.Serializable {
    
    private Long id;
    private String title;
    private String fixedLink;
    private String content;
    private String html;
    private Integer isShow;
    private Integer sortValue;
    private Long categoryId;
    private String categoryName;
    private String tags;
    private Long createUser;
    private String createUserName;
    private Long createTime;
    private Long updateUser;
    private Long updateTime;
    private Long pv;

}
