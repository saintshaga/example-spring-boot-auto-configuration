package cn.saintshaga.example.controller;

import cn.saintshaga.text.service.TextService;
import com.amazonaws.xray.AWSXRay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("text")
    public String text() {
        AWSXRay.beginSubsegment("getCache");
        log.warn(AWSXRay.getCurrentSegment().getTraceId().toString());
        try {
            return service.name();
        } finally {
            AWSXRay.endSubsegment();
        }
    }

}
