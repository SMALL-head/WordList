package com.zyc.wordlistsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyc.wordlistsp.pojo.WordList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ListMapper extends BaseMapper<WordList> {
    int addList(String name);

    int deleteList(String name);

    int deleteWordByName(@Param("list") String list,@Param("word") String word);

    List<String> listAll();

    /**
     * 查看是否存在某个表名
     * @param name 表名
     * @return 如果找到，返回表名，否则，返回空
     */
    String searchByName(@Param("name") String name);

    List<String> listAllByListname(String listname);

    int addWordToList(@Param("name") String listname, @Param("word") String word);
}
