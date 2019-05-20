package cn.saintshaga.example.controller;

import cn.saintshaga.text.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
