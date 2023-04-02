package com.blog.ranger.dao.mapper;

import com.blog.ranger.dao.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @create 2023-04-01-21:00
 */
@Mapper
public interface CommentMapper {

    void InsertComment(Comment comment);

    List<Comment> getCommentsByBlogId(Long blogId);
}
