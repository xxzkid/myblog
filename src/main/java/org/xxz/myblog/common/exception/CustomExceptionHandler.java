package org.xxz.myblog.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.xxz.myblog.common.constant.ResultConst;
import org.xxz.myblog.common.util.WebUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author tt
 */
@Slf4j
@ControllerAdvice // @RestControllerAdvice
public class CustomExceptionHandler {

    public static final String SECURITY_ERROR_VIEW = "security_error";
    public static final String EXCEPTION_ERROR_VIEW = "50x";

    private static final String MSG_KEY = "msg";
    private static final String CODE_KEY = "code";
    private static final String URL_KEY = "url";
    
    @Resource
    private MappingJackson2JsonView mappingJackson2JsonView;
    
    @ExceptionHandler(value = Exception.class)
    public ModelAndView customException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception e) {
        
        log.error("===> custom exception: {}", e.getMessage());
        
        ModelAndView mv = new ModelAndView();
        
        if (isAjax(request, handler)) {
            mv.setView(mappingJackson2JsonView);
        } else {
            mv.setViewName(EXCEPTION_ERROR_VIEW);
        }

        if (e instanceof CustomRuntimeException) {
            CustomRuntimeException ex = (CustomRuntimeException) e;
            mv.addObject(CODE_KEY, ex.exStatus.getCode());
            mv.addObject(MSG_KEY, ex.exStatus.getDesc());
        } else {
            mv.addObject(CODE_KEY, ResultConst.FAIL);
            mv.addObject(MSG_KEY, e.toString());
        }

        mv.addObject(URL_KEY, request.getRequestURL());
        return mv;
    }

    @ExceptionHandler({ SQLException.class, DataAccessException.class })
    public ModelAndView databaseError(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception e) {
        log.error("===> database error: {}", e.getMessage());
        ModelAndView mv = new ModelAndView();
        if (isAjax(request, handler)) {
            mv.setView(mappingJackson2JsonView);
        } else {
            mv.setViewName(EXCEPTION_ERROR_VIEW);
        }
        mv.addObject(MSG_KEY, e.toString());
        mv.addObject(CODE_KEY, ResultConst.DB_ERROR);
        mv.addObject(URL_KEY, request.getRequestURL());
        return mv;
    }

    @ExceptionHandler(value = CustomSecurityException.class)
    public ModelAndView customSecurityExceptionHandler(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception e) throws Exception {
        log.error("===> custom security error: {}", e.getMessage());
        ModelAndView mv = new ModelAndView();

        CustomRuntimeException ex = (CustomRuntimeException) e;

        if (isAjax(request, handler)) {
            mv.setView(mappingJackson2JsonView);
        } else {
            mv.setViewName(SECURITY_ERROR_VIEW);
        }
        mv.addObject(CODE_KEY, ex.exStatus.getCode());
        mv.addObject(MSG_KEY, ex.exStatus.getDesc());
        mv.addObject(URL_KEY, request.getRequestURL());
        return mv;
    }

    private boolean isAjax(HttpServletRequest request, HandlerMethod handler) {
        if (WebUtil.isAjaxRequest(request)) {
            return true;
        }
        if (handler != null) {
            ResponseBody annotation = handler.getMethodAnnotation(ResponseBody.class);
            if (annotation != null) {
                return true;
            }

            RestController restAnnotation = handler.getBeanType().getAnnotation(RestController.class);
            if (restAnnotation != null) {
                return true;
            }
        }
        return false;
    }

}
