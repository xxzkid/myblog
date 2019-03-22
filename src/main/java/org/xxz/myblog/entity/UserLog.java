package org.xxz.myblog.entity;

import lombok.*;

/**
 * 用户登录日志(t_user_log)
 * @author tt
 *
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserLog implements java.io.Serializable {

    private Long id;
    private Long userId;
    private Long loginTime;
    private Integer loginStat;
    private Integer loginErrorCount;
    
}
