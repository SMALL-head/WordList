package com.zyc.wordlistsp.mapper;

import com.zyc.wordlistsp.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User searchByAccount(String account);

    List<User> listAll();

    User checkAuthority(@Param("account") String account, @Param("pwd") String pwd);
}
