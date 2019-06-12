package cn.saintshaga.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class SemaphoreDemo {

    public static void main(String[] args) throws Exception {
        LimitedResources resources = new LimitedResources();

        IntStream.rangeClosed(1, 4).forEach(i -> {
            new Thread(()->{
                log.info("Start thread: {}", Thread.currentThread().getName());
                try {
                    resources.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("End thread: {}", Thread.currentThread().getName());
            }).start();
        });
    }
}
