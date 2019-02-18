package cn.saintshaga.example.service;

import brave.Span;
import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huang on 19-1-11.
 */
@Service
@Slf4j
public class HelloService {

    @Autowired
    private Tracer tracer;

    public String hello() {
        Span span = tracer.nextSpan().name("step1").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(span)){
//            try {
//                Thread.sleep(100);
//            } catch (Exception e) {
//
//            }
            return "hello";
        } finally {
            span.finish();
        }
    }
}
