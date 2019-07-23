package cn.saintshaga.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Phaser;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * print 0-99 alternatively by two threads
 */
@Slf4j
public class Print0To99 {
    private static volatile int count = 0;
    public static void main(String[] args) throws Exception {
        lockWay();
        log.info("Main ends.");
    }

    private static void lockWay() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();
                for(int i=0; i<100; i+=2) {
                    while(count %2 != 0) {
                        try {
                            condition1.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info("{} print {}", Thread.currentThread().getName(), i);
                    count++;
                    condition2.signal();
                }
            } finally {
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            try {
                lock.lock();
                for(int i=1; i<100; i+=2) {
                    while(count % 2 != 1) {
                        try {
                            condition2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info("{} print {}", Thread.currentThread().getName(), i);
                    count++;
                    condition1.signal();
                }
            } finally {
                lock.unlock();
            }
        }).start();
    }

    private static void phaserWay() {
        Phaser phaser1 = new Phaser(2);
        Phaser phaser2 = new Phaser(2);
        new Thread(() -> {
            for(int i=0; i<100; i+=2) {
                log.info("{} print {}", Thread.currentThread().getName(), i);
                phaser1.arriveAndAwaitAdvance();
                phaser2.arriveAndAwaitAdvance();
            }
        }).start();
        new Thread(() -> {
            for(int i=1; i<100; i+=2) {
                phaser1.arriveAndAwaitAdvance();
                log.info("{} print {}", Thread.currentThread().getName(), i);
                phaser2.arriveAndAwaitAdvance();
            }
        }).start();
    }

}
