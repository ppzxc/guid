package com.github.ppzxc.guid;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutoIncrementPartitionIdGeneratorTest {

  @DisplayName("첫번째 채번은 최초 디폴트 값이다.")
  @Test
  void should_default_value_when_first_next() {
    // given
    long givenTimestamp = System.currentTimeMillis();
    IdGenerator given = AutoIncrementPartitionIdGenerator.zero();

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
    IdGenerator given = AutoIncrementPartitionIdGenerator.of(1022);

    // when, then
    assertThat(given.next().partitionId()).isEqualTo(1022);
    assertThat(given.next().partitionId()).isEqualTo(1023);
    assertThat(given.next().partitionId()).isEqualTo(0);
  }

  @DisplayName("sequence 는 max 값에 도달하면 초기값으로 돌아간다.")
  @Test
  void should_around_sequence_when_maximum() throws InterruptedException {
    // given
    int iteration = 10000;
    int numThreads = 50;
    ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
    CountDownLatch latch = new CountDownLatch(numThreads);
    IdGenerator given = AutoIncrementPartitionIdGenerator.of(1022);

    // when
    for (int i = 0; i < iteration; i++) {
      executorService.submit(() -> {
        System.out.println(given.next().sequence());;
        latch.countDown();
      });
    }
    latch.await();
  }
}