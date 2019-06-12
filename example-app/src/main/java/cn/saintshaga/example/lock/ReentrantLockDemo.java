package cn.saintshaga.example.lock;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ReentrantLockDemo {

    @Getter
    @Setter
    private volatile  int value = 0;

    public static void main(String[] args) throws Exception {
        for(int i=0; i<100000; i++) {
            ReentrantLockDemo demo = new ReentrantLockDemo();
            Thread thread1 = new Thread(() -> {
                demo.setValue(2);
            });

            Thread thread2 = new Thread(() -> {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(demo.getValue() != 2) {
                    log.error("Thread2 error");
                }
            });
            thread1.start();
            thread2.start();
        }
        log.info("end main");
    }

}
