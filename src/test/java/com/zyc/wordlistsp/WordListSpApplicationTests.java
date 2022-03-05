package com.zyc.wordlistsp;

import com.zyc.wordlistsp.mapper.UserMapper;
import com.zyc.wordlistsp.pojo.*;
import com.zyc.wordlistsp.service.ListServiceImpl;
import com.zyc.wordlistsp.service.SearchWordServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@SpringBootTest
class WordListSpApplicationTests {

    @Test
    void contextLoads() {
    }

    UserMapper userMapper;

    SearchWordServiceImpl service;

    ListServiceImpl listService;

    @Autowired
    public void setListService(ListServiceImpl listService) {
        this.listService = listService;
    }

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
        Map<String, Object> a = service.search2("update");
        String stringTrans = null;
        Word wordTrans = null;
        if (a.get("String") != null) {
            stringTrans = (String)a.get("String");
        } else {
            wordTrans = (Word) a.get("Word");
        }
        if (wordTrans != null) {
            Map<String, WTContent> translations = wordTrans.getTranslations();
            for (var s : translations.keySet()) {
                WTContent wtContent = translations.get(s);
                List<Translation> translationList = wtContent.getTranslationList();
                System.out.println(s + ":======================");
                for (Translation t : translationList) {
                    System.out.println(t.getTranslation());
                }
            }
        }
        System.out.println(stringTrans);
    }

    @Test
    public void test3() {
        //测试paging
        ListPage<String> list1 = listService.getWordsOnPage("list1", 1);
        System.out.println(list1);
        list1 = listService.getWordsOnPage("list1", 2);
        System.out.println(list1);
        list1 = listService.getWordsOnPage("list1", 3);
        System.out.println(list1);
    }

    @Test
    public void test4() {
        //测试springboot下的mybatis的一级缓存
        userMapper.listAll();
        userMapper.listAll();
    }

}
