package com.blog.ranger.service;

import com.blog.ranger.dao.pojo.Comment;

import java.util.List;

/**
 * @create 2023-04-01-21:09
 */
public interface CommentService {
    void addComment(Comment comment);

    List<Comment> getCommentList(Long blogId);
}
