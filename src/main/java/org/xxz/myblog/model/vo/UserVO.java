package org.xxz.myblog.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author tt
 */
@Setter
@Getter
@ToString
public class UserVO implements java.io.Serializable {

    private static final long serialVersionUID = -5917866271118861427L;
    
    private Long id;
    private String username;
    private String nickName;
    private Date createTime;

}
