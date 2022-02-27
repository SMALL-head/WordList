package com.zyc.wordlistsp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.wordlistsp.mapper.DictMapper;
import com.zyc.wordlistsp.pojo.Word;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class SearchWordServiceImpl implements SearchWordService {


    DictMapper mapper;

    ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setMapper(DictMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String search(String word) {
        String url = "https://dict.youdao.com/w/" + word + "/#keyfrom=dict2.top";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (document == null) {
            return "无查询结果";
        }
        Elements select = document.select("div.trans-container > ul");
        if (select.first() == null) return "无查询结果";
        String html = Objects.requireNonNull(select.first()).html();
        String replace1 = html.replace("<li>", "");

        return replace1.replace("</li>", "").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("\n", "<br>");
    }

    @Override
    public Word search2(String word) {
        String ret;
        String search = mapper.search(word);
        Word word1 = null;
        try {
            word1 = objectMapper.readValue(search, Word.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return word1;
    }

}
