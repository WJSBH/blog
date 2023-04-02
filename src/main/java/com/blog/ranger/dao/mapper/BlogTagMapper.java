package com.blog.ranger.dao.mapper;

import com.blog.ranger.dao.pojo.Tag;
import com.blog.ranger.vo.TagVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @create 2023-03-29-16:01
 */
@Mapper
public interface BlogTagMapper {
    void deleteByTagId(Long tagId);

    void addTag(Long blogId, Long tagId);

    void deleteByBlogId(Long blogId);

    List<Long> getTagIdListByBlogId(Long blogId);

    List<Tag> getTagListByBlogId(Long blogId);

    List<TagVo> getHotTags(Integer limit);

    Integer getTagCount();
}
