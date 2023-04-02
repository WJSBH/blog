package com.blog.ranger.controller;

import com.blog.ranger.service.BlogService;
import com.blog.ranger.service.TagService;
import com.blog.ranger.service.TypeService;
import com.blog.ranger.vo.BLogFrontVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @create 2023-04-01-10:40
 */
@Controller
public class BlogFrontController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(Integer page, Model model) {
        if (page == null) {
            model.addAttribute("page", 1);
            page = 1;
        } else
            model.addAttribute("page", page);

        String searchRegex = null;
        Object searchRegexObj = model.getAttribute("searchRegex");
        if (searchRegexObj != null)
            searchRegex = (String) searchRegexObj;

        List<BLogFrontVo> blogList = blogService.getBlogFrontListBySearch(searchRegex, page, 5);
        model.addAttribute("blogList", blogList);

        model.addAttribute("footerList", blogService.getBlogListByTime(3));
        model.addAttribute("newestList", blogService.getBlogListByTime(3));
        model.addAttribute("blogCount", blogService.getBlogCount());
        model.addAttribute("hotTypes", typeService.getHotTypes(5));
        model.addAttribute("hotTags", tagService.getHotTags(6));
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(name = "searchRegex", defaultValue = "") String searchRegex, RedirectAttributes attributes) {
        attributes.addFlashAttribute("searchRegex", searchRegex);
        return "redirect:/";
    }
}
