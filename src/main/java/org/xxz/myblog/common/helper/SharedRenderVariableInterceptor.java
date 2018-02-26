package org.xxz.myblog.common.helper;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.xxz.myblog.common.util.WebUtil;
import org.xxz.myblog.component.LocalCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器,用于存放渲染视图时需要的的共享变量
 * @author tt
 */
@Configuration
public class SharedRenderVariableInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (modelAndView == null) {
			return;
		}

		String viewName = modelAndView.getViewName();
		if (viewName != null && !viewName.startsWith("redirect:") && !WebUtil.isAjaxRequest(request)) {
			
			modelAndView.addObject("httpInclude", new HttpInclude(request, response));

			modelAndView.addAllObjects(LocalCache.getAll());

			String serverName = request.getServerName();
			String contextPath = request.getContextPath();
			String servletPath = request.getServletPath();
			modelAndView.addObject("currentUrl", "//" + serverName + contextPath + servletPath);
			modelAndView.addObject("serverUrl", "//" + serverName + contextPath);
		}
	}

}