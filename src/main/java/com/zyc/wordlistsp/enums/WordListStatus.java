package com.zyc.wordlistsp.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WordListStatus {
    PRIVATE("私人"),
    PUBLIC("公开");

    final String desc;

    public static WordListStatus getEnumByString(String enumString) {
        for (WordListStatus e : WordListStatus.values()) {
            if (e.toString().equals(enumString)) {
                return e;
            }
        }
        return null;
    }
}
