package cn.saintshaga.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

@Slf4j
public class LimitedResources {

    private Semaphore semaphore = new Semaphore(2);

    public String get() throws InterruptedException {
        semaphore.acquire();
        try {
            log.info("Thread {} get one permit.", Thread.currentThread().getName());
            Thread.sleep(2000);
            return "test";
        } finally {
            log.info("Thread {} is going to release 1 permit", Thread.currentThread().getName());
            semaphore.release();
        }
    }
}
