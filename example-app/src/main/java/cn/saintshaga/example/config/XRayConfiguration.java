package cn.saintshaga.example.config;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import com.amazonaws.xray.strategy.sampling.LocalizedSamplingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.ResourceUtils;

import javax.servlet.Filter;
import java.io.FileNotFoundException;
import java.net.URL;

@Configuration
@Slf4j
public class XRayConfiguration {
    static {
        AWSXRayRecorderBuilder builder = AWSXRayRecorderBuilder.standard();
        URL ruleFile;
        try {
            ruleFile = ResourceUtils.getURL("classpath:sampling-rules.json");
            builder.withSamplingStrategy(new LocalizedSamplingStrategy(ruleFile));
            AWSXRay.setGlobalRecorder(builder.build());
        } catch (FileNotFoundException e) {
            log.error("fail to load x-ray rule file", e);
        }
    }
    @Bean
    public FilterRegistrationBean TracingFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AWSXRayServletFilter("sub-system-example-app"));
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 5);
        return registrationBean;
    }

}
