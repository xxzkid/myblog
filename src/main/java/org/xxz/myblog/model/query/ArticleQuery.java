package org.xxz.myblog.model.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.xxz.myblog.common.bean.BaseQuery;

/**
 * @author tt
 */
@Setter
@Getter
@ToString
public class ArticleQuery extends BaseQuery {

    private Long createUser;
    private Integer isShow;
    private String title;
    private String content;
    private Long categoryId;
    private String tags;

}
