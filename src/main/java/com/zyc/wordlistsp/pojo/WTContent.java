package com.zyc.wordlistsp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 存放时态pattern和翻译数组的类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WTContent {
    String pattern;
    List<Translation> translationList;
}
