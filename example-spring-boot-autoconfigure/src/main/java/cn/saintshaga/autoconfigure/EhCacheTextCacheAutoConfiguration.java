package cn.saintshaga.autoconfigure;

import cn.saintshaga.text.service.EhCacheTextCache;
import cn.saintshaga.text.service.TextCacheService;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by huang on 18-12-27.
 */
@Configuration
@ConditionalOnClass(EhCacheTextCache.class)
@AutoConfigureBefore(DBTextServiceAutoConfiguration.class)
@ConditionalOnMissingBean(TextCacheService.class)
public class EhCacheTextCacheAutoConfiguration {

    @Bean
    public TextCacheService textCacheService() {
        return new EhCacheTextCache();
    }
}
