package cn.saintshaga.example.threadcommunication;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
public class PhaserDemo {

    public static void main(String[] args) throws Exception {
        Phaser phaser = new Phaser(3) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                log.info("end of phase{}, {}", phase, registeredParties);
                return registeredParties == 0;
            }
        };

        IntStream.rangeClosed(1,3).forEach(i ->
            new Thread(() -> {
                log.info("start thread {}", i);
                IntStream.rangeClosed(1,4).forEach(phase -> {
                    log.info("Processing phase{} in thread{}", phase, i);
                    try {
                        TimeUnit.SECONDS.sleep(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    phaser.arriveAndAwaitAdvance();
                });
            }).start()
        );
        log.info("end Main");
    }
}
