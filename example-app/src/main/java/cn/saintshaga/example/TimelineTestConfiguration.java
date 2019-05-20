package cn.saintshaga.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@Component
public @interface TimelineTestConfiguration {

    @AliasFor(annotation = Configuration.class)
    String value() default "";
}
