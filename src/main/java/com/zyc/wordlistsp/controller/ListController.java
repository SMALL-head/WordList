package com.zyc.wordlistsp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyc.wordlistsp.pojo.WordList;
import com.zyc.wordlistsp.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/list")
public class ListController {
    public ListService getListService() {
        return listService;
    }

    @Autowired
    public void setListService(ListService listService) {
        this.listService = listService;
    }

    ListService listService;

    @RequestMapping("/newList")
    public String newList(@RequestParam("listName") String name, Model model) {
        listService.addList(name);
        return "redirect:/list/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        List<String> names = listService.listAll();
        model.addAttribute("nameList", names);
        return "home";
    }

    @RequestMapping("/newList.do")
    @ResponseBody
    public void judgeLegible(String name, HttpServletResponse resp) {
        PrintWriter pw = null;
        resp.setCharacterEncoding("utf-8");
        try {
            pw = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (pw == null) return;
        String s = listService.searchByName(name);
        if (s != null) {
            pw.print("名字重复，请重新输入");
        } else {
            pw.print("名字正确");
        }
    }

    @RequestMapping("/display")
    public String search(@RequestParam("listname") String listname, @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        List<String> words = listService.listAllWordByListname(listname);
        List<String> names = listService.listAll();
        model.addAttribute("nameList", names);
        model.addAttribute("listname", listname);
        model.addAttribute("words", words);

        Page<WordList> searchBookPage = new Page<>(page, 2);
        Page<WordList> page_res = listService.page(searchBookPage, null);
        model.addAttribute("words", page_res);

        return "home";
    }

    @PostMapping("/addWord")
    @ResponseBody
    public String addWordIntoList(@RequestParam("listname") String listname, @RequestParam("word") String word) {
        int i = 0;
        try {
            i = listService.addWordToList(listname, word);
        } catch (Exception e) {
            return "该单词已在表中"; //不满足unique条件，有重复单词
        }
        return "加入成功";
    }
}
