package com.example.tilproject.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Locale;

public enum UrlSection {
    PRESENTATION, TIMEATTACK;

//    @JsonCreator
//    public static UrlSection from(String s){
//        return UrlSection.valueOf(s.toUpperCase());
//    }
}
