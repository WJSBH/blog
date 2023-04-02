package com.blog.ranger.controller;

import com.blog.ranger.service.BlogService;
import com.blog.ranger.service.TypeService;
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
public class TypeFrontController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/type")
    public String type(Integer page, Long curType, Model model) {

        if (page == null) {
            model.addAttribute("page", 1);
            page = 1;
        } else
            model.addAttribute("page", page);



        model.addAttribute("footerList", blogService.getBlogListByTime(3));
        model.addAttribute("hotTypes", typeService.getHotTypes(Integer.MAX_VALUE));
        model.addAttribute("typeCount", typeService.getTypeCount());


        List<BLogFrontVo> blogList = null;
        if (curType != null){
            blogList = blogService.getBlogFrontListByTypeId(curType, page, 5);
            model.addAttribute("curType", curType);
        }
        else
            blogList = blogService.getBlogFrontListBySearch("", page, 5);
        model.addAttribute("blogList", blogList);

        return "type";
    }
}
