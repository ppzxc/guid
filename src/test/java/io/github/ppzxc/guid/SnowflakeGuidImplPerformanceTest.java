package io.github.ppzxc.guid;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;

class SnowflakeGuidImplPerformanceTest {

  @Test
  void nextId_withSingleThread() {
    int iterations = 1000000; // 1 million

    GuidGenerator snowflake = new SnowflakeGuidGeneratorImpl(897);
    long beginTimestamp = System.currentTimeMillis();
    for (int i = 0; i < iterations; i++) {
      snowflake.next();
    }
    long endTimestamp = System.currentTimeMillis();

    long cost = (endTimestamp - beginTimestamp);
    long costMs = iterations / cost;
    System.out.println("Single Thread:: IDs per ms: " + costMs);
  }

  @Test
  void nextId_withMultipleThreads() throws InterruptedException {
    int iterations = 1000000; // 1 million
    int numThreads = 50;

    ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
    CountDownLatch latch = new CountDownLatch(numThreads);

    GuidGenerator snowflake = new SnowflakeGuidGeneratorImpl(897);

    long beginTimestamp = System.currentTimeMillis();
    for (int i = 0; i < iterations; i++) {
      executorService.submit(() -> {
        snowflake.next();
        latch.countDown();
      });
    }

    latch.await();
    long endTimestamp = System.currentTimeMillis();
    long cost = (endTimestamp - beginTimestamp);
    long costMs = iterations / cost;
    System.out.println(numThreads + " Threads:: IDs per ms: " + costMs);
  }
}