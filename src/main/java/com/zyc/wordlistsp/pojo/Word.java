package com.zyc.wordlistsp.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    String word;
    Map<String, WTContent> translations = new HashMap<>();

    public void addTranslation(String title, WTContent content) {
        translations.put(title, content);
    }

    @Override
    public String toString() {
        return "Word{" +
            "word='" + word + '\'' +
            ", translations=" + translations +
            '}';
    }
}
