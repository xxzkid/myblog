package org.xxz.myblog.common.security;

/**
 * @author tt
 */
public interface IUser extends java.io.Serializable {

    Long getId();

    String getUsername();

    String getNickName();

}
