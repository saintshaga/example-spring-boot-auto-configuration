package cn.saintshaga.example.controller;

import lombok.Getter;

import javax.annotation.Nullable;

public enum Page {
    PAGE1("1"),
    PAGE2("2"),
    PAGE_DEFAULT("default");

    @Getter
    private String value;
    private Page(String value) {
        this.value = value;
    }

    public static Page of(@Nullable String value) {
        for(Page page : values()) {
            if(page.getValue().equals(value)) {
                return page;
            }
        }
        return PAGE_DEFAULT;
    }
}
