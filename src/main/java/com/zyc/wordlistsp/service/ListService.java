package com.zyc.wordlistsp.service;

import com.zyc.wordlistsp.pojo.ListPage;
import java.util.List;

public interface ListService{
    int addList(String name);

    List<String> listAll();

    String searchByName(String name);

    List<String> listAllWordByListname(String listname);

    int deleteWordByName(String list, String word);

    int addWordToList(String list, String word);

    ListPage<String> getWordsOnPage(String list, int page);
}
