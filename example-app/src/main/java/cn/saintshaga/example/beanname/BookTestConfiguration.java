package cn.saintshaga.example.beanname;

import cn.saintshaga.example.TimelineTestConfiguration;
import org.springframework.context.annotation.Bean;

@TimelineTestConfiguration
public class BookTestConfiguration {

    @Bean("book-test")
    public Book bookTest() {
        return new Book("book-test");
    }
}
