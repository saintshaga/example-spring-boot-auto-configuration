package cn.saintshaga.example;

import cn.saintshaga.autoconfigure.EhCacheTextCacheAutoConfiguration;
import cn.saintshaga.other.OtherConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Created by huang on 18-12-27.
 */
@SpringBootApplication(exclude = {EhCacheTextCacheAutoConfiguration.class})
@ComponentScan(excludeFilters = {@ComponentScan.Filter(TimelineTestConfiguration.class)})
@Import(OtherConfiguration.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
