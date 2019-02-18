package cn.saintshaga.example.service;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Service;

/**
 * Created by huang on 19-1-14.
 */
@Service
@Slf4j
public class Step2Servcie {

    @Autowired
    private Tracer tracer;

    @NewSpan("step2annotation")
    public String step2() {
//        Span span = tracer.nextSpan().name("step2").start();
//        try (Tracer.SpanInScope ws = tracer.withSpanInScope(span)){
//            try {
//                Thread.sleep(200);
//            } catch (Exception e) {
//
//            }
            return "This is step2";
//        } finally {
//            span.finish();
//        }
    }
}
