package cn.saintshaga.example.beanname;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by huang on 19-2-22.
 */
@Configuration
public class BookConfiguration {

    @Bean("book01")
    public Book book1() {
        return new Book("book1");
    }

    @Bean
    @Qualifier("book02")
    public Book book2() {
        return new Book("book2");
    }

    @Bean
    public Book book3() {
        return new Book("book3");
    }
}
