package org.xxz.myblog.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统资源(t_resource)
 * @author tt
 *
 */
@Setter
@Getter
@ToString
public class SysResource implements java.io.Serializable {
    
    private Long id;
    private String url;
    private Long createUser;
    private Long createTime;

}
