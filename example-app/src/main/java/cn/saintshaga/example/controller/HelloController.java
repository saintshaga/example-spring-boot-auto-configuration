package cn.saintshaga.example.controller;

import cn.saintshaga.text.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by huang on 18-12-27.
 */
@RestController
public class HelloController {

    @Autowired
    private TextService service;

    @RequestMapping("text")
    public String text() {
        return service.name();
    }

    @RequestMapping("socket")
    public void socket() throws Exception {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 1234));
            ByteBuffer buffer = ByteBuffer.wrap("Hello".getBytes());
            socketChannel.write(buffer);
        }
    }

    @RequestMapping("convert/test/{page}")
    public String convertTest(@PathVariable Page page) {
        return page.toString();
    }
}
