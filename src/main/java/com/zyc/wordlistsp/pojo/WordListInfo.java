package com.zyc.wordlistsp.pojo;

import com.zyc.wordlistsp.enums.WordListStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WordListInfo {
    String ListName;
    int size;
    String desc;
    WordListStatus status;

}
