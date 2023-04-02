package com.blog.ranger.controller;

import com.blog.ranger.dao.pojo.Type;
import com.blog.ranger.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @create 2023-03-29-12:27
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String list(Integer page, Model model) {
        if (page == null) {
            model.addAttribute("typeList", typeService.listType(1, 10));
            model.addAttribute("page", 1);
        } else {
            model.addAttribute("typeList", typeService.listType(page, 10));
            model.addAttribute("page", page);
        }
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "/admin/type-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/type-input";
    }

    @PostMapping("/types")
    public String postAdd(Type type, RedirectAttributes attributes) {
        Type savedType = typeService.saveType(type);
        if (savedType == null) {
            attributes.addFlashAttribute("message", "分类已存在");
        } else {
            attributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/types";
    }
    @PostMapping("/types/{id}")
    public String postEdit(Type type, @PathVariable("id") Long id, RedirectAttributes attributes) {
        Type updateType = typeService.updateType(id, type);
        if (updateType == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
