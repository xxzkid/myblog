package org.xxz.myblog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.xxz.myblog.entity.UserLog;

import java.util.List;

/**
 * @author tt
 */
@Mapper
public interface UserLogMapper {

    int save(UserLog userLog);

    int delete(Long id);

    int update(UserLog userLog);

    UserLog findByUserId(Long userId);

    List<UserLog> findAll();

}
