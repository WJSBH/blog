package com.blog.ranger.dao.pojo;

import lombok.Data;

import java.sql.Date;

/**
 * @create 2023-03-28-13:46
 */
@Data
public class User {
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Date createTime;
    private Date updateTime;
}
