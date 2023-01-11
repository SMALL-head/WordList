package com.zyc.wordlistsp.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WordListPage {
    WordListInfo wordListInfo;
    ListPage<String> listPage;
}
