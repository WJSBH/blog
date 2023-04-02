package com.blog.ranger.vo;

import com.blog.ranger.dao.pojo.Tag;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @create 2023-04-01-18:53
 */
@Data
public class BlogDetailVo {
    private Long id;
    private String username;
    private String avatar;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer views;
    private String firstPicture;
    private String flag;
    private String title;
    private String content;
    private List<Tag> tagList;
    private Boolean shareStatement;
    private Boolean commentLabeled;
}
