package org.xxz.myblog.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tt
 */
@Setter
@Getter
@ToString
public class CommentVO implements java.io.Serializable {

    private Long id;
    private String nickName;
    private String site;
    private String createTime;
    private String content;
    private String toNickName;

}
