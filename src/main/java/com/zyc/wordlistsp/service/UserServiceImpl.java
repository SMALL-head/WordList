package com.zyc.wordlistsp.service;

import com.zyc.wordlistsp.mapper.UserMapper;
import com.zyc.wordlistsp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User searchByAccount(String account) {
        return userMapper.searchByAccount(account);
    }

    @Override
    public List<User> listAll() {
        return userMapper.listAll();
    }

    @Override
    public boolean checkAuthority(String account, String pwd) {
        User user = userMapper.checkAuthority(account, pwd);
        return user != null;
    }

    @Override
    public int searchIdByAccount(String account) {
        return userMapper.searchIdByAccount(account);
    }
}
