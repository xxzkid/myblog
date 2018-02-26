package org.xxz.myblog.component;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.xxz.myblog.entity.SysParam;
import org.xxz.myblog.mapper.SysParamMapper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tt
 */
@Slf4j
@Component
public class LocalCache {

    private static final Map<String, String> datas = new HashMap<>(50);

    @Resource
    private SysParamMapper sysParamMapper;

    @PostConstruct
    public void init() {
        log.info("====> loading sys_param data ");
        List<SysParam> list = sysParamMapper.findAll();
        if(list != null && !list.isEmpty()) {
            for(SysParam sysParam : list) {
                datas.put(sysParam.getParamName(), sysParam.getParamValue());
            }
        }
        log.info("====> loading sys_param data finish ");
    }

    /**
     * 获取value
     * @param key
     * @return
     */
    public static String getValue(String key) {
        return datas.get(key);
    }

    /**
     * 获取所有key
     * @return
     */
    public static Map<String, String> getAll() {
        return Maps.newHashMap(datas);
    }

    public static void put(String paramName, String paramValue) {
        if (StringUtils.isBlank(paramName)) {
            throw new IllegalArgumentException("paramName must not be blank");
        }
        datas.put(paramName, paramValue);
    }
}
