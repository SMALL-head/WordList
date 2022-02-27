package com.zyc.wordlistsp.mapper;

import com.zyc.wordlistsp.pojo.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DictMapper {
    String search(@Param("word") String word);
}
