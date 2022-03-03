package com.zyc.wordlistsp.service;

import com.zyc.wordlistsp.mapper.ListMapper;
import com.zyc.wordlistsp.pojo.ListPage;
import com.zyc.wordlistsp.pojo.PageConfig;
import com.zyc.wordlistsp.pojo.WordList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListServiceImpl implements ListService {
    ListMapper mapper;

    PageConfig pageConfig;

    @Autowired
    public void setPageConfig(PageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }

    @Autowired
    public void setMapper(ListMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public int addList(String name) {
        if (mapper.searchByName(name) != null) {
            //表名重复
            return 0;
        } else {
            return mapper.addList(name);
        }
    }

    @Override
    public List<String> listAll() {
        return mapper.listAll();
    }

    @Override
    public String searchByName(String name) {
        return mapper.searchByName(name);
    }

    @Override
    public List<String> listAllWordByListname(String listname) {
        return mapper.listAllByListname(listname);
    }

    @Override
    public int deleteWordByName(String list, String word) {
        return mapper.deleteWordByName(list, word);
    }

    @Override
    public int addWordToList(String list, String word) {
        return mapper.addWordToList(list, word);
    }

    @Override
    public ListPage<String> getWordsOnPage(String list, int page) {
        //获取配置文件中
        int limitation = pageConfig.getLimitation();
        // 获取分页信息
        List<String> wordsOnPage = mapper.searchByListnameInPage(list, (page-1) * limitation, limitation);
        //总共多少条记录
        int size = mapper.searchSizeByListname(list);
        //总共多少页
        int pages = (size % limitation) == 0 ? size / limitation : size / limitation + 1;

        return new ListPage<>(page, pages, wordsOnPage, limitation);

    }
}
