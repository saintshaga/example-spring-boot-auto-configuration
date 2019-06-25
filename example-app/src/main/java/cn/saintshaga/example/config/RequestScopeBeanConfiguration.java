package cn.saintshaga.example.config;

import cn.saintshaga.example.beanname.Book;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by saintshaga on 2019/6/24.
 */
@Configuration
public class RequestScopeBeanConfiguration {

    @Bean("singletonBook")
    public Book singletonBook() {
        return new Book("signle");
    }

    @Bean(name = "requestBook")
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Book requestBook() {
        return new Book("request");
    }

    @Bean("prototypeBook")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Book prototypeBook() {
        return new Book("prototype");
    }
}
