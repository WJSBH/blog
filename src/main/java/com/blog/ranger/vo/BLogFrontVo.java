package com.blog.ranger.vo;

import com.blog.ranger.dao.pojo.Tag;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @create 2023-03-31-16:35
 */
@Data
public class BLogFrontVo {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String avatar;
    private String username;
    private Timestamp updateTime;
    private List<Tag> tagList;
    private Integer views;
}
