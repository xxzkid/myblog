package org.xxz.myblog.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.xxz.myblog.entity.SysResource;
import org.xxz.myblog.model.dto.ResourceDTO;
import org.xxz.myblog.model.vo.ResourceVO;

/**
 * @author tt
 */
public final class SysResourceConvertUtil {


    public static ResourceVO sysResource2ResourceVO(SysResource sysResource, ResourceVO resourceVO) {
        if(sysResource == null) {
            throw new IllegalArgumentException("sysResource is null");
        }
        if(resourceVO == null) {
            resourceVO = new ResourceVO();
        }
        resourceVO.setUrl(sysResource.getUrl());
        return resourceVO;
    }


    public static SysResource resourceDTO2SysResource(ResourceDTO resourceDTO, SysResource sysResource) {
        if(resourceDTO == null) {
            throw new IllegalArgumentException("resourceDto is null");
        }
        if(sysResource == null) {
            sysResource = new SysResource();
        }
        sysResource.setUrl(resourceDTO.getUrl());
        sysResource.setCreateUser(resourceDTO.getUpdateUser());
        return sysResource;
    }

    public static List<ResourceVO> stringList2ResourceVO(List<String> data) {
        if(data == null) {
            return Collections.emptyList();
        }
        List<ResourceVO> list = new ArrayList<>(data.size());
        for (String url : data) {
            list.add(new ResourceVO(url));
        }
        return list;
    }
}
