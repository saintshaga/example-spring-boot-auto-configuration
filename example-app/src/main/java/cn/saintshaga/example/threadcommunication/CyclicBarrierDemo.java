package cn.saintshaga.example.threadcommunication;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
public class CyclicBarrierDemo {

    public static void main(String[] args) throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(4);
        IntStream.rangeClosed(1, 4).forEach(i -> {
            new Thread(() -> {
                log.info("Thread {} started", i);
                try {
                    TimeUnit.SECONDS.sleep(i);
                } catch (InterruptedException e) {
                    log.error("", e);
                }
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                log.info("Thread {} end", i);
            }).start();
        });
        log.info("main ended.");
    }
}
