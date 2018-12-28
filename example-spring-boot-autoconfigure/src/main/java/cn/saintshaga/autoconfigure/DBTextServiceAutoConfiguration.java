package cn.saintshaga.autoconfigure;

import cn.saintshaga.text.service.DBTextService;
import cn.saintshaga.text.service.TextCacheService;
import cn.saintshaga.text.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by huang on 18-12-27.
 */
@Configuration
@AutoConfigureAfter(EhCacheTextCacheAutoConfiguration.class)
@ConditionalOnClass(DBTextService.class)
@ConditionalOnMissingBean(TextService.class)
public class DBTextServiceAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(TextCacheService.class)
    public TextService textServiceWithoutCache() {
        return new DBTextService();
    }

    @Bean
    @ConditionalOnBean(TextCacheService.class)
    @Autowired
    public TextService textServiceWithCache(TextCacheService cacheService) {
        return new DBTextService(cacheService);
    }
}
