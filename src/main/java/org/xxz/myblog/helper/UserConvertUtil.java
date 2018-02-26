package org.xxz.myblog.helper;

import org.xxz.myblog.common.util.DozerUtil;
import org.xxz.myblog.entity.User;
import org.xxz.myblog.model.dto.UserDTO;

/**
 * @author tt
 */
public final class UserConvertUtil {
    
    public static User userDTO2User(UserDTO userDTO, User user) {
        if(user == null) {
            user = new User();
        }
        DozerUtil.map(userDTO, user);
        return user;
    }

}
