package com.blog.ranger.dao.mapper;

import com.blog.ranger.dao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @create 2023-03-28-17:34
 */
@Mapper
public interface UserMapper {
     User checkUser(@Param("username") String username, @Param("password") String password);
}
