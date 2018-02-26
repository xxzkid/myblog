package org.xxz.myblog.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.xxz.myblog.common.Result;
import org.xxz.myblog.common.annotation.Security;
import org.xxz.myblog.common.security.IUser;
import org.xxz.myblog.common.security.SessionUserContext;
import org.xxz.myblog.entity.Category;
import org.xxz.myblog.model.dto.ArticleDTO;
import org.xxz.myblog.model.dto.CategoryDTO;
import org.xxz.myblog.model.query.ArticleQuery;
import org.xxz.myblog.model.vo.ArticleVO;
import org.xxz.myblog.service.AdminArticleService;
import org.xxz.myblog.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author tt
 */
@Controller
@RequestMapping(value = "/admin/article")
public class AdminArticleController {

    @Autowired
    private AdminArticleService adminArticleService;
    @Autowired
    private CategoryService categoryService;
    
    @Security
    @GetMapping(value = "/myList")
    public ModelAndView myList(ArticleQuery articleQuery) {
        ModelAndView mv = new ModelAndView();
        IUser user = SessionUserContext.get();
        articleQuery.setCreateUser(user.getId());
        List<ArticleVO> list = adminArticleService.getUserArticleAll(articleQuery);
        PageInfo<ArticleVO> pageInfo = new PageInfo<>(list);
        mv.addObject("page", pageInfo);
        mv.addObject("categorys", userCategorys(user.getId()));
        mv.setViewName("admin/article/myList");
        return mv;
    }

    @Security
    @GetMapping(value = "/add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/article/add");
        
        IUser user = SessionUserContext.get();
        mv.addObject("categorys", userCategorys(user.getId()));
        
        return mv;
    }
    
    private List<Category> userCategorys(long userId) {
        List<Category> categorys = categoryService.getCategorys(userId);
        return categorys;
    }

    @Security
    @GetMapping(value = "/edit/{articleId}")
    public ModelAndView edit(@PathVariable("articleId") Long articleId) {
        ModelAndView mv = new ModelAndView();
        IUser user = SessionUserContext.get();
        Result<ArticleVO> result = adminArticleService.editArticle(articleId, user);
        if(result.getCode() == 0) {
            ArticleVO articleVO = result.getData();
            mv.addObject("article", articleVO);
            mv.addObject("categorys", userCategorys(user.getId()));
            mv.setViewName("admin/article/add");
            return mv;
        } else {
            mv.setViewName("error");
            return mv;
        }
    }

    @Security
    @GetMapping(value = "/delete/{articleId}")
    @ResponseBody
    public Result<?> delete(@PathVariable("articleId") Long articleId) {
        IUser user = SessionUserContext.get();
        return adminArticleService.deleteArticle(articleId, user);
    }

    @Security
    @PostMapping(value = "/add")
    @ResponseBody
    public Result<?> articleSave(@Valid ArticleDTO articleDTO, BindingResult result) {
        if(result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return Result.fail(errors.get(0).getDefaultMessage(), errors);
        }

        IUser user = SessionUserContext.get();

        articleDTO.setUpdateUser(user.getId());
        articleDTO.setUpdateUserName(user.getNickName());
        return adminArticleService.addOrUpdateArticle(articleDTO);
    }
    
    @Security
    @PostMapping(value = "/addCategory")
    @ResponseBody
    public Result<?> addCategory(@Valid CategoryDTO categoryDTO, BindingResult result) {
        if(result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return Result.fail(errors.get(0).getDefaultMessage(), errors);
        }
        IUser user = SessionUserContext.get();
        categoryDTO.setUpdateUser(user.getId());
        categoryDTO.setUpdateUserName(user.getNickName());
        return categoryService.addCategory(categoryDTO);
    }
    
    @Security
    @RequestMapping(value = "/categorys")
    @ResponseBody
    public Result<?> categorys() {
        IUser user = SessionUserContext.get();
        List<Category> categorys = categoryService.getCategorys(user.getId());
        return Result.success("", categorys);
    }

}
