package com.zyc.wordlistsp.mapper;

import com.zyc.wordlistsp.pojo.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictMapper {
    String search(@Param("word") String word);

    List<String> searchByPrefix(@Param("prefix") String prefix);
}
