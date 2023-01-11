package com.zyc.wordlistsp.service;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.wordlistsp.mapper.DictMapper;
import com.zyc.wordlistsp.pojo.Word;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /**
     * 版本2的单词查询方法
     * @param word 查询的单词
     * @return 若本地库中能查询到，可以使用get("Word")得到Word封装对象；否则将get("String")返回有道的简明翻译
     */
    @Override
    public Map<String, Object> search2(String word) {
        String ret;
        String search = mapper.search(word);
        if (search == null) {
            String search1 = search(word);
            return new HashMap<>(){{this.put("String", search1);}};
        }
        Word word1 = null;
        try {
            word1 = objectMapper.readValue(search, Word.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Word finalWord = word1;
        return new HashMap<>(){{this.put("Word", finalWord);}};
    }

    @Override
    @Cacheable(value = {"searchPrefix"}, cacheManager = "prefixSearchCacheManager", keyGenerator = "searchPrefixKeyGenerator")
    public List<String> searchByPrefix(String prefix) {
        if (StringUtils.isEmpty(prefix)) {
            return null;
        }
        return mapper.searchByPrefix(prefix);
    }


}
