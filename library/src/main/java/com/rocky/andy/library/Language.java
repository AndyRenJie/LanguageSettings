package com.rocky.andy.library;

/**
 * Created by Andy.Ren on 2018/6/21.
 */

public enum Language {

    LANGUAGE_CN(0),
    LANGUAGE_TW(1),
    LANGUAGE_US(2),
    LANGUAGE_KR(3),
    LANGUAGE_JP(4);

    Language(int value) {
        this.value = value;
    }

    public int value;
}
