package com.zyc.wordlistsp.service;

import com.zyc.wordlistsp.pojo.ListPage;
import java.util.List;

public interface ListService{
//    int addList(String name);

    int addList(Integer uid, String name);

    List<String> listAll();

    List<String> listAllByUid(int uid);

    String searchByName(String name, int uid);

    List<String> listAllWordByListname(int uid, String listname);

    int deleteWordByName(String list, String word, int uid);

    int addWordToList(String list, String word, int uid);

    ListPage<String> getWordsOnPage(String list, int page, int uid);


}
