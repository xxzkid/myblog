package org.xxz.myblog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.xxz.myblog.entity.SysResource;

import java.util.List;

/**
 * @author tt
 */
@Mapper
public interface SysResourceMapper {

    int saveList(List<SysResource> list);

    int delete(Long id);

    int update(SysResource e);

    SysResource findById(Long id);

    List<SysResource> findAll();

    List<SysResource> findByUserId(long userId);
}
