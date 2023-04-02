package com.blog.ranger.dao.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @create 2023-03-28-13:42
 */
@Data
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private Timestamp createTime;
    private Long blogId;
    private String avatar;
}
