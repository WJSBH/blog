package com.blog.ranger.dao.mapper;

import com.blog.ranger.dao.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @create 2023-03-29-14:40
 */
@Mapper
public interface TagMapper {

    List<Tag> getAllTags();
    List<Tag> getTags(@Param("offset") Integer offset, @Param("rows") Integer rows);

    Tag getTagByName(@Param("name") String name);

    void addTag(@Param("name") String name);

    void deleteById(@Param("id") Long id);

    Tag getTagById(@Param("id") Long id);

    void updateTag(@Param("id") Long id, @Param("name") String name);

}
