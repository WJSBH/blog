package com.blog.ranger.dao.mapper;

import com.blog.ranger.dao.pojo.Blog;
import com.blog.ranger.vo.BLogFrontVo;
import com.blog.ranger.vo.BlogAdminVo;
import com.blog.ranger.vo.BlogDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @create 2023-03-29-16:54
 */
@Mapper
public interface BlogMapper {
    Blog getBlogById(Long id);

    List<BlogAdminVo> getBlogList(Integer offset, Integer rows, String title, Long typeId, Boolean recommend);

    void insertBlog(Blog blog);

    void updateBlog(@PathVariable("bid") Long bid, Blog blog);

    void deleteBlog(Long id);

    Blog getBlogByTitle(String title);

    List<BLogFrontVo> getBlogFrontListBySearch(String regex, Integer offset, Integer rows);

    List<Blog> getBlogListByTime(Integer limit);

    Integer getBlogCount();

    List<BLogFrontVo> getBlogFrontListByTypeId(Long typeId, Integer offset, Integer rows);

    List<BLogFrontVo> getBlogFrontListByTagId(Long tagId, Integer offset, Integer rows);

    BlogDetailVo getBlogDetailById(Long id);

    void incrViewsById(Long blogId);
}
