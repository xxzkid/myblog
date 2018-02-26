package org.xxz.myblog.service;

import org.xxz.myblog.common.Result;
import org.xxz.myblog.common.security.IUser;
import org.xxz.myblog.model.vo.ResourceVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author tt
 */
public interface ResourceService {

    List<ResourceVO> getAllResource(long userId);

    Result<List<ResourceVO>> saveResource(HttpServletRequest request, IUser user);

}
