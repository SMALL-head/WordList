package com.zyc.wordlistsp.controller;

import com.zyc.wordlistsp.pojo.User;
import com.zyc.wordlistsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    UserService userService;

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    @Qualifier("userServiceImpl")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/search")
    public String search(Model model) {
        List<User> users = userService.listAll();
        if (users.size() == 0) {
            model.addAttribute("msg", "没有人");
        } else {
            model.addAttribute("msg", users.get(0));
        }

        return "test";
    }

    @PostMapping("/checkLogin")
    public String checkLogin(@RequestParam("account") String account,@RequestParam("pwd") String pwd, Model model) {
        if (!userService.checkAuthority(account, pwd)) {
            model.addAttribute("fail", "账号密码错误");
            return "login/login";
        } else {
            return "redirect:/list/home";
        }
    }

    @GetMapping(value = {"/login", "/login.html"})
    public String login(Model model) {
        return "login/login";
    }
}
