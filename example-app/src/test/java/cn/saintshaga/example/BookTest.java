package cn.saintshaga.example;

import cn.saintshaga.example.beanname.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BookTest.TestConfiguration.class})
public class BookTest {

    @Test
    public void test() {

    }

    @Configuration
    static class TestConfiguration {
        @Bean
        Book book20() {
            return new Book("book20");
        }
    }
}
