package org.xxz.myblog.service;

import org.xxz.myblog.common.Result;
import org.xxz.myblog.common.security.IUser;
import org.xxz.myblog.model.dto.SysParamDTO;
import org.xxz.myblog.model.vo.SysParamVO;

import java.util.List;

/**
 * @author tt
 */
public interface SystemService {

    /**
     * 获取所有系统参数
     * @return
     */
    SysParamVO getAllSysParam();

    /**
     * 添加参数
     * @param sysParamDTO
     * @param user
     * @return
     */
    Result<?> save(SysParamDTO sysParamDTO, IUser user);
}
