package cn.saintshaga.example.controller;

import cn.saintshaga.text.service.TextService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huang on 18-12-27.
 */
@RestController
public class HelloController {

    @Autowired
    private TextService service;

    @RequestMapping("text")
    public String text() {
        return service.name();
    }

    @RequestMapping("convert/test/{page}")
    public String convertTest(@PathVariable Page page) {
        return page.toString();
    }
}
