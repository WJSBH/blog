package com.blog.ranger.service.impl;

import com.blog.ranger.dao.mapper.CommentMapper;
import com.blog.ranger.dao.pojo.Comment;
import com.blog.ranger.service.CommentService;
import com.blog.ranger.utils.StrMapIntUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @create 2023-04-01-21:09
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void addComment(Comment comment) {
        comment.setCreateTime(new Timestamp(System.currentTimeMillis()));
        Integer i = StrMapIntUtils.strs2Int(comment.getNickname(), comment.getEmail()) % 3 + 1;
        comment.setAvatar("/images/avatar" + i + ".jpg");
        commentMapper.InsertComment(comment);
    }

    @Override
    public List<Comment> getCommentList(Long blogId) {
        return commentMapper.getCommentsByBlogId(blogId);
    }
}
