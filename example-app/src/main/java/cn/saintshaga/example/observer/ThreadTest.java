package cn.saintshaga.example.observer;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * Created by huang on 19-1-17.
 */
@Slf4j
public class ThreadTest implements Runnable {

    private Object lock = new Object();
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        if(name.startsWith("a")) {
            step1();
        } else {
            step2();
        }
    }

    private void step1() {
        synchronized (lock) {
            try {
                log.info("step1: {} start at {}", Thread.currentThread().getName(), new Date().toString());
                Thread.sleep(2000);
                log.info("step1: {} end at {}", Thread.currentThread().getName(), new Date().toString());
            } catch (Exception e) {}
        }
    }

    private void step2() {
        synchronized (this) {
            try {
                log.info("step2: {} start at {}", Thread.currentThread().getName(), new Date().toString());
                Thread.sleep(3000);
                log.info("step2: {} end at {}", Thread.currentThread().getName(), new Date().toString());
            } catch (Exception e) {}
        }
    }

    public static void main(String[] args) {
        ThreadTest test = new ThreadTest();
        Thread thread1 = new Thread(test, "aThread");
        Thread thread2 = new Thread(test, "bThread");
        thread1.start();
        thread2.start();
    }

}
