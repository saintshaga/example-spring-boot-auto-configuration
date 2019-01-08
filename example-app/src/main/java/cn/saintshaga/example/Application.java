package cn.saintshaga.example;

import cn.saintshaga.autoconfigure.EhCacheTextCacheAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by huang on 18-12-27.
 */
@SpringBootApplication(exclude = {EhCacheTextCacheAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
