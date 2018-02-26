package org.xxz.myblog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.xxz.myblog.entity.SysParam;

import java.util.List;

/**
 * @author tt
 */
@Mapper
public interface SysParamMapper {
    
    int save(SysParam e);

    int delete(String paramName);

    int update(SysParam e);

    SysParam findByName(String paramName);

    List<SysParam> findAll();

}
