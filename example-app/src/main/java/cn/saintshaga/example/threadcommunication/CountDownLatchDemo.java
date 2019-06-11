package cn.saintshaga.example.threadcommunication;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(3);
        List<Thread> threads = Lists.newArrayList();
        IntStream.range(0, 4).forEach(i -> {
            Thread thread = new Thread(() -> {
                log.info("Thread {} started", i);
                try {
                    TimeUnit.SECONDS.sleep(i+1);
                } catch (InterruptedException e) {
                    log.error("", e);
                }
                log.info("Thread {} end", i);
                latch.countDown();
            });
            threads.add(thread);
            thread.start();
        });
//        threads.forEach(thread -> {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        latch.await();
        log.info("main ended.");
    }
}
