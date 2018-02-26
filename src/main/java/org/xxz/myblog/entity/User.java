package org.xxz.myblog.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.xxz.myblog.common.security.IUser;

/**
 * 用户(t_user)
 * @author tt
 *
 */
@Getter
@Setter
@ToString
public class User implements IUser {

    private Long id;
    private String username;
    private String password;
    private String nickName;
    private Long createTime;
    
}
