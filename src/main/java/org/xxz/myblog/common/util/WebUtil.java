package org.xxz.myblog.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * web工具类
 * @author tt
 */
public final class WebUtil {

    /**
     * 是否为ajax请求
     * 
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        String uri = request.getRequestURI();
        if ((requestType != null && "XMLHttpRequest".equals(requestType)) || //
                uri.endsWith(".json")) {
            return true;
        }
        return false;
    }

}
