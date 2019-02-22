package cn.saintshaga.other;

import cn.saintshaga.example.beanname.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by huang on 19-2-22.
 */
@Configuration
public class OtherConfiguration {

    @Bean
    public Book book4() {
        return new Book("book4");
    }
}
