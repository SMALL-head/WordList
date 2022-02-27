package com.zyc.wordlistsp;

import com.zyc.wordlistsp.mapper.UserMapper;
import com.zyc.wordlistsp.pojo.User;
import com.zyc.wordlistsp.pojo.Word;
import com.zyc.wordlistsp.service.SearchWordServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WordListSpApplicationTests {

    @Test
    void contextLoads() {
    }

    UserMapper userMapper;

    SearchWordServiceImpl service;

    @Autowired
    public void setService(SearchWordServiceImpl service) {
        this.service = service;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Test
    public void test1(){
        List<User> users = userMapper.listAll();
        System.out.println(users);
    }

    @Test
    public void test2() {
        Word a = service.search2("argentite");
        System.out.println(a);
    }

}
