package com.zyc.wordlistsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyc.wordlistsp.pojo.WordList;

import java.util.List;

public interface ListService extends IService<WordList> {
    int addList(String name);

    List<String> listAll();

    String searchByName(String name);

    List<String> listAllWordByListname(String listname);

    int deleteWordByName(String list, String word);

    int addWordToList(String list, String word);
}
