package com.blog.ranger.service;

import com.blog.ranger.dao.pojo.Tag;
import com.blog.ranger.vo.TagVo;

import java.util.List;

/**
 * @create 2023-03-29-15:08
 */
public interface TagService {
    List<Tag> getTagPage(Integer page, Integer rows);

    Tag saveTag(Tag tag);

    void deleteTag(Long id);

    Tag getTagById(Long id);

    void updateTag(Long id, Tag tag);

    List<Tag> getAllTags();

    List<Long> getTagListByBlogId(Long id);

    void updateBlogTag(String tags, Long id);

    List<TagVo> getHotTags(Integer limit);

    Integer getTagCount();
}
