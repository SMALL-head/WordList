package com.zyc.wordlistsp.service;

import com.zyc.wordlistsp.pojo.Word;

import java.util.Map;

public interface SearchWordService {
    String search(String word);

    Map<String, Object> search2(String word);
}
