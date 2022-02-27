package com.zyc.wordlistsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyc.wordlistsp.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User searchByAccount(String account);

    List<User> listAll();

    User checkAuthority(@Param("account") String account, @Param("pwd") String pwd);
}
