package cn.saintshaga.example.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

@Slf4j
public class ExecutorServiceDemo {

    public static void main(String[] args) throws Exception {
        int nThreads = 5;
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        CyclicBarrier barrier = new CyclicBarrier(4, () -> log.info("a round finished"));
        List<Future<Integer>> results = new ArrayList<>();
        IntStream.rangeClosed(1, 8).forEach(i -> results.add(executorService.submit(() -> {
            log.info("task {} begin, and nwo we have {} threads", i, executorService.getPoolSize());
            try {
                TimeUnit.SECONDS.sleep(i%4);
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            log.info("task {} end, now we have {} threads", i, executorService.getPoolSize());
            return i;
        })));
        results.stream().forEach(future -> {
            try {
                log.warn("Result is {}", future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        log.info("main ends");
    }


}
