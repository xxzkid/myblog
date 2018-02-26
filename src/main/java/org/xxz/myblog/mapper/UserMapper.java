package org.xxz.myblog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.xxz.myblog.entity.User;

import java.util.List;

/**
 * @author tt
 */
@Mapper
public interface UserMapper {

    int save(User user);

    int delete(Long id);

    int updateByUsername(User user);

    User findByUsername(String username);

    List<User> findAll();

}
