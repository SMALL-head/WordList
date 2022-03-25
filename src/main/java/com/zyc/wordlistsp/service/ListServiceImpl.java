package com.zyc.wordlistsp.service;

import com.zyc.wordlistsp.mapper.ListMapper;
import com.zyc.wordlistsp.pojo.ListPage;
import com.zyc.wordlistsp.config.PageConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListServiceImpl implements ListService {
    ListMapper mapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    PageConfig pageConfig;

    @Autowired
    public void setPageConfig(PageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }

    @Autowired
    public void setMapper(ListMapper mapper) {
        this.mapper = mapper;
    }

//    @Override
//    @Transactional
//    public int addList(String name) {
//        if (mapper.searchByName(name) != null) {
//            //表名重复
//            sqlSessionFactory.openSession().flushStatements();
//            return 0;
//        } else {
//            return mapper.addList(name);
//        }
//    }

    @Override
    public int addList(Integer uid, String name) {
        return mapper.addListByUid(uid, name);
    }

    @Override
    public List<String> listAll() {
        return mapper.listAll();
    }

    @Override
    public List<String> listAllByUid(int uid) {
        return mapper.listAllByUid(uid);
    }

    @Override
    public String searchByName(String name, int uid) {
        return mapper.searchByName(name, uid);
    }

    @Override
    public List<String> listAllWordByListname(int uid, String listname) {
        return mapper.listAllByListname(uid, listname);
    }

    @Override
    public int deleteWordByName(String list, String word, int uid) {
        return mapper.deleteWordByName(list, word, uid);
    }

    @Override
    public int addWordToList(String list, String word, int uid) {

        return mapper.addWordToList(list, word, uid);

    }

    @Override
    public ListPage<String> getWordsOnPage(String list, int page, int uid) {
        //获取配置文件中
        int limitation = pageConfig.getLimitation();

        // 获取分页信息
        List<String> wordsOnPage = mapper.searchByListnameInPage(list, (page-1) * limitation, limitation, uid);
        //总共多少条记录
        int size = mapper.searchSizeByListname(list, uid);
        //总共多少页
        int pages = (size % limitation) == 0 ? size / limitation : size / limitation + 1;
        if (size == 0) pages = 1; // 如果该页没有单词那么pages应该为1，而不是上面的式子得到的0
        return new ListPage<>(page, pages, wordsOnPage, limitation);

    }
}
