package com.github.ppzxc.guid;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutoIncrementPartitionedGuidGeneratorTest {

  @DisplayName("첫번째 채번은 최초 디폴트 값이다.")
  @Test
  void should_default_value_when_first_next() {
    // given
    long givenTimestamp = System.currentTimeMillis();
    GuidGenerator given = AutoIncrementPartitionedGuidGenerator.zero();

    // when
    GUID actual = given.next();

    // then
    assertThat(actual.id()).isNotNegative().isPositive();
    assertThat(actual.timestamp()).isGreaterThanOrEqualTo(givenTimestamp);
    assertThat(actual.partitionId()).isZero();
    assertThat(actual.sequence()).isZero();
  }

  @DisplayName("partition id 는 max 값에 도달하면 초기값으로 돌아간다.")
  @Test
  void should_around_partition_id_when_maximum() {
    // given
    GuidGenerator given = AutoIncrementPartitionedGuidGenerator.of(1022);

    // when, then
    assertThat(given.next().partitionId()).isEqualTo(1022);
    assertThat(given.next().partitionId()).isEqualTo(1023);
    assertThat(given.next().partitionId()).isEqualTo(0);
  }

  @Test
  public void nextId_withSingleThread() {
    int iterations = 1000000; // 1 million

    GuidGenerator generator = AutoIncrementPartitionedGuidGenerator.zero();
    long beginTimestamp = System.currentTimeMillis();
    for (int i = 0; i < iterations; i++) {
      generator.next();
    }
    long endTimestamp = System.currentTimeMillis();

    long cost = (endTimestamp - beginTimestamp);
    long costMs = iterations/cost;
    System.out.println("Single Thread:: IDs per ms: " + costMs);
  }

  @Test
  public void nextId_withMultipleThreads() throws InterruptedException {
    int iterations = 1000000; // 1 million
    int numThreads = 50;

    ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
    CountDownLatch latch = new CountDownLatch(numThreads);

    GuidGenerator generator = AutoIncrementPartitionedGuidGenerator.zero();

    long beginTimestamp = System.currentTimeMillis();
    for(int i = 0; i < iterations; i++) {
      executorService.submit(() -> {
        generator.next();
        latch.countDown();
      });
    }

    latch.await();
    long endTimestamp = System.currentTimeMillis();
    long cost = (endTimestamp - beginTimestamp);
    long costMs = iterations/cost;
    System.out.println(numThreads + " Threads:: IDs per ms: " + costMs);
  }
}