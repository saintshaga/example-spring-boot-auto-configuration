package cn.saintshaga.autoconfigure;

import cn.saintshaga.text.service.EhCacheTextCache;
import cn.saintshaga.text.service.TextCacheService;
import cn.saintshaga.text.service.TextService;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by huang on 18-12-28.
 */
public class TestDBTextServiceAutoConfiguration {

    private final ApplicationContextRunner runner = new ApplicationContextRunner().withConfiguration(AutoConfigurations.of(
            DBTextServiceAutoConfiguration.class,
            EhCacheTextCacheAutoConfiguration.class
        ));

    @Test
    public void textServiceWithoutCache_whenNoTextCacheService() {
        runner.withClassLoader(new FilteredClassLoader(EhCacheTextCache.class)).run(
            context -> {
                assertThat(context).hasSingleBean(TextService.class);
                assertThat(context.getBean(TextService.class)).isSameAs(
                        context.getBean(DBTextServiceAutoConfiguration.class).textServiceWithoutCache());
            }
        );
    }

    @Test
    public void textServiceWithCache_whenHasCacheClass() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(TextService.class);
            assertThat(context).hasSingleBean(TextCacheService.class);
            assertThat(context.getBean(TextService.class)).isSameAs(
                    context.getBean(DBTextServiceAutoConfiguration.class).textServiceWithCache(
                            context.getBean(TextCacheService.class)
                    )
            );
        });
    }
}
