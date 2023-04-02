package com.blog.ranger.controller;

import com.blog.ranger.dao.pojo.Tag;
import com.blog.ranger.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @create 2023-03-29-14:33
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;
    @GetMapping("/tag")
    public String tag(Integer page, Model model){
        if (page == null) {
            model.addAttribute("tagList", tagService.getTagPage(1, 10));
            model.addAttribute("page", 1);
        } else {
            model.addAttribute("tagList", tagService.getTagPage(page, 10));
            model.addAttribute("page", page);
        }
        return "admin/tags";
    }


    @GetMapping("/tag/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tag-input";
    }
    @PostMapping("/tag")
    public String postAdd(Tag tag, RedirectAttributes attributes) {
        Tag savedTag = tagService.saveTag(tag);
        if (savedTag == null) {
            attributes.addFlashAttribute("message", "标签已存在");
        } else {
            attributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/tag";
    }

    @GetMapping("/tag/{id}/input")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("tag",tagService.getTagById(id));
        return "admin/tag-input";
    }
    @PostMapping("/tag/{id}")
    public String postEdit(@PathVariable("id") Long id, Tag tag, RedirectAttributes attributes) {
        tagService.updateTag(id, tag);
        attributes.addFlashAttribute("message", "修改成功");
        return "redirect:/admin/tag";
    }

    @GetMapping("/tag/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tag";
    }
}
