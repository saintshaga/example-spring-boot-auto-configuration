package cn.saintshaga.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Created by saintshaga on 2019/6/13.
 */
@Slf4j
public class LockConditionDemo {
    public static void main(String[] args) throws Exception {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        IntStream.rangeClosed(2,3).forEach(i -> {
        new Thread(() -> {
            log.info("start thread {}", i);
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            log.info("Thread {} end", i);
        }).start();});
        new Thread(() -> {
            log.info("start thread 1");
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            log.info("end thread 1");
        }).start();
        log.info("end main");
    }
}
