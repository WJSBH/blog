package com.blog.ranger.controller;

import com.blog.ranger.service.BlogService;
import com.blog.ranger.service.TagService;
import com.blog.ranger.vo.BLogFrontVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @create 2023-04-01-10:41
 */
@Controller
public class TagFrontController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;

    @GetMapping("/tag")
    public String tag(Integer page, Long curTag, Model model) {
        if (page == null) {
            model.addAttribute("page", 1);
            page = 1;
        } else
            model.addAttribute("page", page);

        model.addAttribute("footerList", blogService.getBlogListByTime(3));
        model.addAttribute("hotTags", tagService.getHotTags(Integer.MAX_VALUE));
        model.addAttribute("tagCount", tagService.getTagCount());

        List<BLogFrontVo> blogList = null;
        if (curTag != null){
            blogList = blogService.getBlogFrontListByTagId(curTag, page, 5);
            model.addAttribute("curTag", curTag);
        }
        else
            blogList = blogService.getBlogFrontListBySearch("", page, 5);
        model.addAttribute("blogList", blogList);

        return "tag";
    }

}
