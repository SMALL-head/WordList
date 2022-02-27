package com.zyc.wordlistsp.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyc.wordlistsp.mapper.ListMapper;
import com.zyc.wordlistsp.pojo.WordList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListServiceImpl extends ServiceImpl<ListMapper, WordList> implements ListService {
    ListMapper mapper;

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
}
