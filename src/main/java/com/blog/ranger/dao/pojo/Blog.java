package com.blog.ranger.dao.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @create 2023-03-28-13:33
 */
@Data
public class Blog {

    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private Boolean shareStatement = false;
    private Boolean commentLabeled = false;
    private Boolean published = false;
    private Boolean recommend = false;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Long typeId;

}
