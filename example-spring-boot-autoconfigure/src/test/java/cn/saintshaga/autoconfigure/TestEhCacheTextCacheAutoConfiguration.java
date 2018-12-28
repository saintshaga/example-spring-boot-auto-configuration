package cn.saintshaga.autoconfigure;

import cn.saintshaga.text.service.EhCacheTextCache;
import cn.saintshaga.text.service.TextCacheService;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by huang on 18-12-28.
 */
public class TestEhCacheTextCacheAutoConfiguration {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(EhCacheTextCacheAutoConfiguration.class));

    @Test
    public void noEhcacheBean_whenNoEhCacheTextCacheClass() {
        this.runner.withClassLoader(new FilteredClassLoader(EhCacheTextCache.class)).run(context -> {
            assertThat(context).doesNotHaveBean(TextCacheService.class);
        });
    }
    @Test
    public void hasEhcacheBean_whenHasEhCacheTextCacheClass() {
        this.runner.run(context -> {
            assertThat(context).hasSingleBean(TextCacheService.class);
            assertThat(context.getBean(TextCacheService.class)).isSameAs(
                    context.getBean(EhCacheTextCacheAutoConfiguration.class).textCacheService());
        });
    }
}
