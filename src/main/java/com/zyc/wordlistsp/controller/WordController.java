package com.zyc.wordlistsp.controller;

import com.zyc.wordlistsp.pojo.WTContent;
import com.zyc.wordlistsp.pojo.Word;
import com.zyc.wordlistsp.service.ListService;
import com.zyc.wordlistsp.service.SearchWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/word")
public class WordController {
    SearchWordService service;
    ListService listService;

    @Autowired
    public void setListService(ListService listService) {
        this.listService = listService;
    }

    public SearchWordService getService() {
        return service;
    }

    @Autowired
    public void setService(SearchWordService service) {
        this.service = service;
    }

    @RequestMapping("/search")
    public String search(@RequestParam("search") String word,
                         @RequestParam("uid") int uid,
                         Model model) {
        String result = service.search(word);
        model.addAttribute("res", result);
        model.addAttribute("search_word", word);
        model.addAttribute("nameList", listService.listAllByUid(uid));
        if ("无查询结果".equals(result)) {
            model.addAttribute("addWord", false);
        } else {
            model.addAttribute("addWord", true);
        }
        model.addAttribute("uid", uid);
        return "home";
    }

    @RequestMapping("/search2")
    public String search2(@RequestParam("search") String word,
                          @RequestParam("uid") int uid,
                          Model model) {
        String stringRes = null;
        Word wordRes = null;
        Map<String, Object> resMap = service.search2(word);
        if (resMap.get("String") != null) {
            stringRes = (String) resMap.get("String");
        } else {
            wordRes = (Word) resMap.get("Word");
        }

        if (wordRes != null) {
            model.addAttribute("search_word", word);
            model.addAttribute("nameList", listService.listAllByUid(uid));
//            model.addAttribute("addWord", true);

            //如何包装结果呢？
            Map<String, WTContent> translations = wordRes.getTranslations();
            model.addAttribute("translationMap", translations);
            model.addAttribute("uid", uid);
            return "localSearched";
        }

        if (stringRes != null) {
            model.addAttribute("res", stringRes);
            model.addAttribute("search_word", word);
            model.addAttribute("nameList", listService.listAll());
            if ("无查询结果".equals(stringRes)) {
                model.addAttribute("addWord", false);
            } else {
                model.addAttribute("addWord", true);
            }
        }

        model.addAttribute("uid", uid);

        return "home";
    }

    @RequestMapping("/searchWord")
    @ResponseBody
    @SuppressWarnings("all")
    public void searchWord(@RequestParam("word") String word, HttpServletResponse resp) {
        PrintWriter writer = null;
        resp.setCharacterEncoding("utf-8");
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = service.search(word);
        writer.println(result);
    }

    @RequestMapping("/deleteWord")
    public String deleteWord(@RequestParam("listname") String list,
                             @RequestParam("word") String word,
                             @RequestParam("curPage") int curPage,
                             @RequestParam("uid") int uid) {
        listService.deleteWordByName(list, word, uid);
        return "redirect:/list/display?listname=" + list + "&page=" + curPage + "&uid=" + uid;
    }

    @RequestMapping("/prefix")
    @ResponseBody
    public List<String> searchByPrefix(@RequestParam("prefix") String prefix) {
        return service.searchByPrefix(prefix);
    }
}
