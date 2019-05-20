package cn.saintshaga.example;

import cn.saintshaga.example.beanname.Book;
import org.springframework.context.annotation.Bean;

@TimelineTestConfiguration
public class TestConfiguration {

    @Bean
    Book book10() {
        return new Book("book10");
    }
}
