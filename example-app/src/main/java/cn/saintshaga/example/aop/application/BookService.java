package cn.saintshaga.example.aop.application;

import cn.saintshaga.example.beanname.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookService {

    public Book createBook(String name) {
        return new Book(name);
    }

    public Book queryBook() {
        log.info("query booking");
        return null;
    }
}
