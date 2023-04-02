package com.blog.ranger.controller;

import com.blog.ranger.dao.pojo.Comment;
import com.blog.ranger.service.BlogService;
import com.blog.ranger.service.CommentService;
import com.blog.ranger.vo.BlogDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @create 2023-04-01-18:32
 */
@Controller
public class DetailController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/detail")
    public String detail(Long blogId, Model model) {

        blogService.incrViewsById(blogId);
        model.addAttribute("footerList", blogService.getBlogListByTime(3));
        BlogDetailVo curBlog = blogService.getBlogDetailById(blogId);
        model.addAttribute("curBlog", curBlog);
        if (curBlog.getCommentLabeled())
            model.addAttribute("commentList", commentService.getCommentList(blogId));

        return "blog";
    }

    @PostMapping("/comment")
    public String comment(Comment comment) {
        commentService.addComment(comment);
        return "redirect:/detail?blogId=" + comment.getBlogId().toString();
    }

}
