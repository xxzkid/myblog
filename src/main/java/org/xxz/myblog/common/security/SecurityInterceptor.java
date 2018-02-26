package org.xxz.myblog.common.security;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.xxz.myblog.common.annotation.Security;
import org.xxz.myblog.common.constant.UserConstants;
import org.xxz.myblog.common.exception.CustomSecurityException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.xxz.myblog.common.enums.ErrorEnum.NOT_LOGIN;

/**
 * @author tt
 */
public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Security security = handlerMethod.getMethodAnnotation(Security.class);
            if (security == null) {
                return true;
            }
            
            // 需要校验是否登录
            IUser user = (IUser)request.getSession().getAttribute(UserConstants.SESSION_USER);
            if(user == null) {
                throw new CustomSecurityException(NOT_LOGIN);
            }
            SessionUserContext.put(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
