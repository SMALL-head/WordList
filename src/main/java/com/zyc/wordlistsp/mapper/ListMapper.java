package com.zyc.wordlistsp.mapper;

import com.zyc.wordlistsp.pojo.WordList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ListMapper {
    @Deprecated
    int addList(String name);

    int addListByUid(Integer uid, String name);

    int deleteList(String name);

    int deleteWordByName(@Param("list") String list,
                         @Param("word") String word,
                         @Param("uid") int uid);

    List<String> listAll();

    List<String> listAllByUid(Integer uid);

    /**
     * 查看是否存在某个表名
     * @param name 表名
     * @return 如果找到，返回表名，否则，返回空
     */
    String searchByName(@Param("name") String name, @Param("uid") int uid);

    List<String> listAllByListname(int uid, String listname);

    int addWordToList(@Param("listname") String listname,
                      @Param("word") String word,
                      @Param("uid") int uid);

    List<String> searchByListnameInPage(@Param("listname") String listname,
                                        @Param("page") int page,
                                        @Param("limitation") int limitation,
                                        @Param("uid") int uid);

    int searchSizeByListname(@Param("listname") String listname,
                             @Param("uid") int uid);
}
