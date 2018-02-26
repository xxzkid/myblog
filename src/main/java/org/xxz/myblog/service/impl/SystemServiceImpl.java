package org.xxz.myblog.service.impl;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.xxz.myblog.common.Result;
import org.xxz.myblog.common.security.IUser;
import org.xxz.myblog.component.LocalCache;
import org.xxz.myblog.entity.SysParam;
import org.xxz.myblog.helper.SysParamConvertUtil;
import org.xxz.myblog.mapper.SysParamMapper;
import org.xxz.myblog.model.dto.SysParamDTO;
import org.xxz.myblog.model.vo.SysParamVO;
import org.xxz.myblog.service.SystemService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author tt
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Resource
    private SysParamMapper sysParamMapper;

    @Override
    public SysParamVO getAllSysParam() {
        List<SysParam> all = sysParamMapper.findAll();
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        Map<String, String> map = convertMap(all);
        SysParamVO sysParamVO = new SysParamVO();
        sysParamVO.setAuthor(map.get("author"));
        sysParamVO.setUploadUrl(map.get("upload_url"));
        sysParamVO.setTitle(map.get("title"));
        sysParamVO.setWxpay(map.get("wxpay"));
        return sysParamVO;
    }

    @Transactional
    @Override
    public Result<?> save(SysParamDTO sysParamDTO, IUser user) {
        Assert.notNull(sysParamDTO, "sysParamDTO must not be null");

        // 判断参数是否存在
        SysParam param = sysParamMapper.findByName(sysParamDTO.getParamName());
        long now = System.currentTimeMillis();
        int row = -1;
        if (param == null) {
            SysParam sysParam = SysParamConvertUtil.sysParamDTO2SysParam(sysParamDTO, null);
            sysParam.setCreateTime(now);
            sysParam.setUpdateTime(now);
            sysParam.setCreateUser(user.getId());
            sysParam.setUpdateUser(user.getId());
            row = sysParamMapper.save(sysParam);

            LocalCache.put(sysParam.getParamName(), sysParam.getParamValue());

            return row > 0 ? Result.success("添加成功") : Result.fail("添加失败");
        } else {
            param = SysParamConvertUtil.sysParamDTO2SysParam(sysParamDTO, param);
            param.setUpdateUser(user.getId());
            param.setUpdateTime(now);
            row = sysParamMapper.update(param);

            LocalCache.put(param.getParamName(), param.getParamValue());

            return row > 0 ? Result.success("修改成功") : Result.fail("修改失败");
        }
    }

    private Map<String, String> convertMap(List<SysParam> params) {
        Map<String, String> map = Maps.newHashMap();
        for (SysParam s : params) {
            map.put(s.getParamName(), s.getParamValue());
        }
        return map;
    }
}
