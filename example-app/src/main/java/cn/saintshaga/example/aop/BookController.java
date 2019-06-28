package cn.saintshaga.example.aop;

import cn.saintshaga.example.aop.application.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookService service;

    @RequestMapping("createBook")
    public String createBook() {
        service.createBook("test");
        service.queryBook();
        return "OK";
    }
}
