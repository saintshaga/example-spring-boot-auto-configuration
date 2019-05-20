package cn.saintshaga.example;

import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ApplicationTest {

    @Test
    public void test() {
        ComponentScan scan = Application.class.getAnnotation(ComponentScan.class);
        System.out.println(scan);
        System.out.println(Arrays.stream(Application.class.getAnnotations()).collect(Collectors.toList()));
        System.out.println(Application.class.getAnnotation(EnableAutoConfiguration.class));
    }
}
