package cn.saintshaga.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class MyAdvice {

    @Before("cn.saintshaga.example.aop.SystemPointCut.inAopPackage()")
    public void logArgsAspect(JoinPoint joinPoint) {
        log.info("Before method: {}", Arrays.toString(joinPoint.getArgs()));
    }

    @After("cn.saintshaga.example.aop.SystemPointCut.inAopPackage()")
    public void logAfterAspect() {
        log.info("After method.");
    }
}
