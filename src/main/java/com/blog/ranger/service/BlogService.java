package com.blog.ranger.service;

import com.blog.ranger.dao.pojo.Blog;
import com.blog.ranger.vo.BLogFrontVo;
import com.blog.ranger.vo.BlogAdminVo;
import com.blog.ranger.vo.BlogDetailVo;

import java.util.List;

/**
 * @create 2023-03-29-16:50
 */
public interface BlogService {
    Blog getBlog(Long id);
    List<BlogAdminVo> listBlog(Integer page, Integer rows, Blog blog);
    Blog saveBlog(Blog blog, String tags);
    Blog updateBlog(Long id, Blog blog);
    void deleteBlog(Long id);
    List<BLogFrontVo> getBlogFrontListBySearch(String regex, Integer page, Integer rows);
    List<BLogFrontVo> getBlogFrontListByTypeId(Long typeId, Integer page, Integer rows);
    List<BLogFrontVo> getBlogFrontListByTagId(Long tagId, Integer page, Integer rows);

    List<Blog> getBlogListByTime(Integer i);

    Integer getBlogCount();
    BlogDetailVo getBlogDetailById(Long blogId);

    void incrViewsById(Long blogId);
}
