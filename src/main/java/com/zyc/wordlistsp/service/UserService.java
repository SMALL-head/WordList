package com.zyc.wordlistsp.service;

import com.zyc.wordlistsp.pojo.User;

import java.util.List;

public interface UserService {
    User searchByAccount(String account);
    List<User> listAll();
    boolean checkAuthority(String account, String pwd);
    int searchIdByAccount(String account);
}
