package com.blog.ranger.controller;

import com.blog.ranger.dao.pojo.Blog;
import com.blog.ranger.service.BlogService;
import com.blog.ranger.service.TagService;
import com.blog.ranger.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2023-03-28-20:16
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;


    @GetMapping("/blogs")
    public String blogs(Integer page, Model model) {

        // 获取查询条件
        Blog blog = new Blog();
        Object typeId = model.getAttribute("typeId");
        if (typeId != null && (Long) typeId != 0)
            blog.setTypeId((Long) typeId);
        Object title = model.getAttribute("title");
        if (title != null)
            blog.setTitle((String) title);
        Object recommend = model.getAttribute("recommend");
        if (recommend != null)
            blog.setRecommend((Boolean) recommend);
        else
            blog.setRecommend(false);

        // 分页查询
        if (page == null) {
            model.addAttribute("page", 1);
            model.addAttribute("blogList", blogService.listBlog(1, 10, blog));
        } else {
            model.addAttribute("page", page);
            model.addAttribute("blogList", blogService.listBlog(page, 10, blog));
        }

        // 查出分类列表
        if (model.getAttribute("typeList") == null)
            model.addAttribute("typeList", typeService.getAllTypes());

        return "admin/blogs";
    }

    @PostMapping("/blogSearch")
    public String searchList(@RequestParam(name = "title", defaultValue = "") String title,
                             @RequestParam(name = "typeId", defaultValue = "0") Long typeId,
                             @RequestParam(name = "recommend", defaultValue = "false") Boolean recommend,
                             RedirectAttributes attributes) {
        attributes.addFlashAttribute("title", title);
        attributes.addFlashAttribute("typeId", typeId);
        attributes.addFlashAttribute("recommend", recommend);
        return "redirect:/admin/blogs";
    }


    @GetMapping("/blogs/input")
    public String blogInput(Model model) {
        model.addAttribute("typeList", typeService.getAllTypes());
        model.addAttribute("tagList", tagService.getAllTags());
        model.addAttribute("curBlog", new Blog());
        model.addAttribute("curTagList", new ArrayList<>());
        return "admin/blog-input";
    }

    @GetMapping("/blogs/{id}/input")
    public String blogEdit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("typeList", typeService.getAllTypes());
        model.addAttribute("tagList", tagService.getAllTags());
        model.addAttribute("curBlog", blogService.getBlog(id));
        model.addAttribute("curTagList", tagService.getTagListByBlogId(id));
        return "admin/blog-input";
    }

    @PostMapping("/blogs/input")
    public String postAdd(Blog blog, @RequestParam(name = "tag", defaultValue = "") String tags) {
        blogService.saveBlog(blog, tags);
        return "redirect:/admin/blogs";
    }

    @PostMapping("/blogs/input/{id}")
    public String postEdit(Blog blog, @RequestParam(name = "tag", defaultValue = "") String tags, @PathVariable("id") Long id) {
        blogService.updateBlog(id, blog);
        tagService.updateBlogTag(tags, id);
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }
}
