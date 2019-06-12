package cn.saintshaga.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        new Thread(() -> {
            log.info("begin thread 1");
            lock.readLock().lock();
            try {
                log.info("get read lock from thread 1");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        }).start();

        new Thread(() -> {
            log.info("begin thread 2");
            lock.readLock().lock();
            try {
                log.info("get read lock from thread 2");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        }).start();

        new Thread(() -> {
            log.info("begin thread 3");
            lock.writeLock().lock();
            try {
                log.info("get write lock from thread 3");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        }).start();
    }


}
