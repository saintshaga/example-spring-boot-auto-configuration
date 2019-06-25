package cn.saintshaga.example.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPageConverter implements Converter<String, Page> {
    @Override
    public Page convert(String source) {
        return Page.of(source);
    }
}
