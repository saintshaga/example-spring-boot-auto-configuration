package cn.saintshaga.example.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemPointCut {

    @Pointcut("within(cn.saintshaga.example.aop.application..*)")
    public void inAopPackage() {}
}
