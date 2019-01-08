package cn.saintshaga.example.config;

import cn.saintshaga.text.service.EhCacheTextCache;
import cn.saintshaga.text.service.TextCacheService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by huang on 19-1-8.
 */
@Configuration
public class EhCacheTextCacheConfiguration {
    @Bean
    public TextCacheService textCacheService() {
        return new EhCacheTextCache();
    }
}
