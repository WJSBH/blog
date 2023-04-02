package com.blog.ranger.service.impl;

import com.blog.ranger.dao.mapper.BlogMapper;
import com.blog.ranger.dao.mapper.BlogTagMapper;
import com.blog.ranger.dao.pojo.Blog;
import com.blog.ranger.service.BlogService;
import com.blog.ranger.utils.MarkdownUtils;
import com.blog.ranger.vo.BLogFrontVo;
import com.blog.ranger.vo.BlogAdminVo;
import com.blog.ranger.vo.BlogDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @create 2023-03-29-16:53
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private BlogTagMapper blogTagMapper;


    @Override
    public Blog getBlog(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public List<BlogAdminVo> listBlog(Integer page, Integer rows, Blog blog) {
        String title = blog.getTitle();
        if (title == null || "".equals(title))
            title = "%_%";
        else
            title = "%" + title + "%";
        Long typeId = blog.getTypeId();
        Boolean recommend = blog.getRecommend();
        Integer offset = (page - 1) * rows;
        List<BlogAdminVo> blogList = blogMapper.getBlogList(offset, rows, title, typeId, recommend);
        for (int i = 0; i < blogList.size(); i++) {
            if (blogList.get(i).getTypeName() == null)
                blogList.get(i).setTypeName("未分类");
        }
        return blogList;
    }

    @Override
    public Blog saveBlog(Blog blog, String tags) {

        if (blog.getViews() == null)
            blog.setViews(0);
        if (blog.getCreateTime() == null)
            blog.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if (blog.getUpdateTime() == null)
            blog.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        if (blog.getFlag() == null)
            blog.setFlag("原创");

        blogMapper.insertBlog(blog);

        blog = blogMapper.getBlogByTitle(blog.getTitle());

        if (tags != null && !"".equals(tags)) {
            String[] tagsArray = tags.split(",");

            for (int i = 0; i < tagsArray.length; i++) {
                blogTagMapper.addTag(blog.getId(), Long.parseLong(tagsArray[i]));
            }
        }

        return blog;
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        blog.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        blogMapper.updateBlog(id, blog);
        return blogMapper.getBlogById(id);
    }

    @Override
    public void deleteBlog(Long id) {
        blogTagMapper.deleteByBlogId(id);
        blogMapper.deleteBlog(id);
    }

    @Override
    public List<BLogFrontVo> getBlogFrontListBySearch(String regex, Integer page, Integer rows) {
        if (regex == null || "".equals(regex))
            regex = "%_%";
        else
            regex = "%" + regex + "%";
        List<BLogFrontVo> blogFrontList = blogMapper.getBlogFrontListBySearch(regex, (page - 1) * rows, rows);
        return preproBlogFrontList(blogFrontList);
    }

    @Override
    public List<BLogFrontVo> getBlogFrontListByTypeId(Long typeId, Integer page, Integer rows) {

        List<BLogFrontVo> blogFrontList = blogMapper.getBlogFrontListByTypeId(typeId, (page - 1) * rows, rows);
        return preproBlogFrontList(blogFrontList);
    }

    @Override
    public List<BLogFrontVo> getBlogFrontListByTagId(Long tagId, Integer page, Integer rows) {
        List<BLogFrontVo> blogFrontList = blogMapper.getBlogFrontListByTagId(tagId, (page - 1) * rows, rows);
        return preproBlogFrontList(blogFrontList);
    }

    @Override
    public List<Blog> getBlogListByTime(Integer i) {
        return blogMapper.getBlogListByTime(i);
    }

    @Override
    public Integer getBlogCount() {
        return blogMapper.getBlogCount();
    }

    @Override
    public BlogDetailVo getBlogDetailById(Long blogId) {
        BlogDetailVo blog = blogMapper.getBlogDetailById(blogId);
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        blog.setTagList(blogTagMapper.getTagListByBlogId(blogId));
        return blog;
    }

    @Override
    public void incrViewsById(Long blogId) {
        blogMapper.incrViewsById(blogId);
    }

    private List<BLogFrontVo> preproBlogFrontList(List<BLogFrontVo> blogFrontList) {
        for (int i = 0; i < blogFrontList.size(); i++) {
            BLogFrontVo bLogFrontVo = blogFrontList.get(i);
            bLogFrontVo.setTagList(blogTagMapper.getTagListByBlogId(bLogFrontVo.getId()));
            if (bLogFrontVo.getContent().length() > 128)
                bLogFrontVo.setContent(bLogFrontVo.getContent().substring(0, 127));
        }
        return blogFrontList;
    }
}
