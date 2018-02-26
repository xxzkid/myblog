package org.xxz.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xxz.myblog.common.annotation.Security;
import org.xxz.myblog.model.TestVO;

import javax.validation.Valid;
import java.sql.SQLException;

/**
 * @author tt
 */
@Controller
public class WelcomeController {

    @RequestMapping(value = {"/", "", "/index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/article/list");
        return mv;
    }

    @RequestMapping("test")
    public ModelAndView testVelocity() {
        ModelAndView mv = new ModelAndView("test");
        mv.addObject("user", "水门");
        return mv;
    }

    @RequestMapping(value = {"/json"})
    @ResponseBody
    public String testJson() {
        return "hello, mvc-velocity";
    }

    @RequestMapping("validator")
    @ResponseBody
    public String testValidator(@Valid TestVO testVO, BindingResult result) {
        if (result.hasErrors()) {
            return result.getAllErrors().get(0).getDefaultMessage();
        }
        return "validator";
    }

    @RequestMapping("exception")
    @ResponseBody
    public String testException() throws SQLException {
        throw new SQLException();
    }

    @RequestMapping("security")
    @ResponseBody
    @Security
    public String testSecurity() {
        return "security";
    }

}
