package com.blog.ranger.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @create 2023-03-30-21:44
 */
@Data
public class BlogAdminVo {
    private Long id;
    private String title;
    private Boolean recommend;
    private Timestamp updateTime;
    private String typeName;
}
