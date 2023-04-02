package com.blog.ranger;

import com.blog.ranger.dao.mapper.UserMapper;
import com.blog.ranger.dao.pojo.Blog;
import com.blog.ranger.dao.pojo.User;
import com.blog.ranger.service.BlogService;
import com.blog.ranger.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class RangerApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BlogService blogService;

    @Test
    void contextLoads() {
        User user = userMapper.checkUser("1", "5");
        System.out.println(user);
    }

    @Test
    void t1(){
        System.out.println(DigestUtils.md5DigestAsHex("111111".getBytes()));
    }

    @Test
    void t2(){
        Blog blog = new Blog();
        blog.setTitle("%_%");
        blog.setRecommend(false);
        System.out.println(blogService.listBlog(1, 10, blog));
    }
}
