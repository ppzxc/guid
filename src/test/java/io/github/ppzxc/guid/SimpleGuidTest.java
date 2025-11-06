package io.github.ppzxc.guid;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class SimpleGuidTest {

  @Timeout(2 * 1000L)
  @DisplayName("generate GUID")
  @Test
  void t0() throws InterruptedException {
    GuidGenerator guidGenerator = new SimpleGuidGenerator();
    int threadCount = 10;
    int iterationCount = 999;

    CountDownLatch countDownLatch = new CountDownLatch(threadCount);
    List<Thread> threadList = new ArrayList<>();
    for (int i = 0; i < threadCount; i++) {
      threadList.add(new Thread(() -> {
        for (int i1 = 0; i1 < iterationCount; i1++) {
          Guid guid = guidGenerator.next();
          assertThat(guid).isNotNull();
        }
        countDownLatch.countDown();
      }));
    }
    threadList.forEach(Thread::start);
    countDownLatch.await();
  }
}
