package com.blog.ranger.service.impl;

import com.blog.ranger.dao.mapper.BlogTagMapper;
import com.blog.ranger.dao.mapper.TagMapper;
import com.blog.ranger.dao.pojo.Tag;
import com.blog.ranger.service.TagService;
import com.blog.ranger.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @create 2023-03-29-15:10
 */
@Transactional
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;
    @Autowired
    BlogTagMapper blogTagMapper;

    @Override
    public List<Tag> getTagPage(Integer page, Integer rows) {
        return tagMapper.getTags((page - 1) * rows, rows);
    }

    @Override
    public Tag saveTag(Tag tag) {
        String name = tag.getName();
        if (tagMapper.getTagByName(name) == null) {
            tagMapper.addTag(name);
            return tagMapper.getTagByName(name);
        } else
            return null;
    }

    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteById(id);
        blogTagMapper.deleteByTagId(id);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagMapper.getTagById(id);
    }

    @Override
    public void updateTag(Long id, Tag tag) {
        tagMapper.updateTag(id, tag.getName());
    }

    @Override
    public List<Tag> getAllTags() {
        return tagMapper.getAllTags();
    }

    @Override
    public List<Long> getTagListByBlogId(Long id) {
        return blogTagMapper.getTagIdListByBlogId(id);
    }

    @Override
    public void updateBlogTag(String tags, Long id) {
        blogTagMapper.deleteByBlogId(id);
        if (tags != null && !"".equals(tags)) {
            String[] tagsArray = tags.split(",");
            for (int i = 0; i < tagsArray.length; i++) {
                blogTagMapper.addTag(id, Long.parseLong(tagsArray[i]));
            }
        }
    }

    @Override
    public List<TagVo> getHotTags(Integer limit) {
        return blogTagMapper.getHotTags(limit);
    }

    @Override
    public Integer getTagCount() {
        return blogTagMapper.getTagCount();
    }
}
