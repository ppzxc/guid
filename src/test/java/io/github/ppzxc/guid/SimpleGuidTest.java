package io.github.ppzxc.guid;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Test;

class SimpleGuidTest {

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
//          System.out.println(guidGenerator.next());
        }
        countDownLatch.countDown();
      }));
    }
    threadList.forEach(Thread::start);
    countDownLatch.await();
  }
}
