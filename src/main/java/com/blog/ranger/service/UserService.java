package com.blog.ranger.service;

import com.blog.ranger.dao.pojo.User;

/**
 * @create 2023-03-28-17:32
 */
public interface UserService {
    User checkUser(String username, String password);
}
