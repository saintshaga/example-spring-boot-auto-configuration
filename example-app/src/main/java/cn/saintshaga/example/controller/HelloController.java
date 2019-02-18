package cn.saintshaga.example.controller;

import brave.Tracer;
import cn.saintshaga.example.event.CustomEvent;
import cn.saintshaga.example.event.SubCustomEvent;
import cn.saintshaga.example.service.HelloService;
import cn.saintshaga.example.service.Step2Servcie;
import cn.saintshaga.text.service.TextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huang on 18-12-27.
 */
@RestController
@Slf4j
public class HelloController {

    @Autowired
    private TextService service;

    @Autowired
    private Step2Servcie step2Servcie;

    @Autowired
    private HelloService helloService;

    @Autowired
    private Tracer tracer;

    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping("text")
    public String text() {
        log.info("asdf" + tracer.currentSpan().context().traceIdString());
        return helloService.hello() + step2Servcie.step2();
    }

    @RequestMapping("event")
    public String event() {
        CustomEvent event = new CustomEvent("parent","parent");
        publisher.publishEvent(event);
        publisher.publishEvent(new SubCustomEvent("child", "child"));
        return "asdfasdf";
    }
}
