package com.blog.ranger.service.impl;

import com.blog.ranger.dao.mapper.UserMapper;
import com.blog.ranger.dao.pojo.User;
import com.blog.ranger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;


/**
 * @create 2023-03-28-17:32
 */
@Service
@Transactional
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userMapper.checkUser(username, password);
        return user;
    }
}
