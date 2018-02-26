package org.xxz.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xxz.myblog.common.Result;
import org.xxz.myblog.common.annotation.Security;
import org.xxz.myblog.common.security.IUser;
import org.xxz.myblog.common.security.SessionUserContext;
import org.xxz.myblog.model.dto.SysParamDTO;
import org.xxz.myblog.model.vo.SysParamVO;
import org.xxz.myblog.service.SystemService;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author tt
 */
@Controller
@RequestMapping(value = "/admin/system")
public class AdminSystemController {

    @Resource
    private SystemService systemService;

    @Security
    @RequestMapping(value = "/setup")
    public ModelAndView setup() {
        ModelAndView mv = new ModelAndView("admin/system/setup");
        SysParamVO sysParamVO = systemService.getAllSysParam();
        mv.addObject("vo", sysParamVO);
        return mv;
    }

    @Security
    @PostMapping(value = "/setupSave")
    @ResponseBody
    public Result<?> setupSave(@Valid SysParamDTO sysParamDTO, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail(result.getAllErrors().get(0).getDefaultMessage());
        }
        IUser user = SessionUserContext.get();
        return systemService.save(sysParamDTO, user);
    }

}
