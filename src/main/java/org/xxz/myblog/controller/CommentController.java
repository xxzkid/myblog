package org.xxz.myblog.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.xxz.myblog.common.Result;
import org.xxz.myblog.model.dto.CommentDTO;
import org.xxz.myblog.model.query.CommentQuery;
import org.xxz.myblog.model.vo.CommentVO;
import org.xxz.myblog.service.CommentService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author tt
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @RequestMapping(value = "/list/{articleId}")
    @ResponseBody
    public Result<?> list(@PathVariable("articleId") Long articleId,
                          @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(required = false, defaultValue = "10")Integer pageSize) {
        CommentQuery commentQuery = new CommentQuery();
        commentQuery.setArticleId(articleId);
        commentQuery.setPageNum(pageNum);
        commentQuery.setPageSize(pageSize);
        List<CommentVO> list = commentService.getCommentsByArticleId(commentQuery);
        PageInfo<CommentVO> page = new PageInfo<>(list);
        return Result.success("ok", page);
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public Result<?> save(@Valid CommentDTO commentDTO, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail(result.getAllErrors().get(0).getDefaultMessage());
        }
        return commentService.save(commentDTO);
    }

}
