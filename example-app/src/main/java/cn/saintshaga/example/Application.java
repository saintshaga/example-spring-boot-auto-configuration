package cn.saintshaga.example;

import cn.saintshaga.autoconfigure.EhCacheTextCacheAutoConfiguration;
import cn.saintshaga.other.OtherConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * Created by huang on 18-12-27.
 */
@SpringBootApplication(exclude = {EhCacheTextCacheAutoConfiguration.class})
@Import(OtherConfiguration.class)
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
