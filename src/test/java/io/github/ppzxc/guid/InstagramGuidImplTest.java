package io.github.ppzxc.guid;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

class InstagramGuidImplTest {

  @DisplayName("생성된 ID 는 PartitionGuid 객체에서 파싱할 수 있다.")
  @RepeatedTest(10)
  void should_equals_when_given() {
    // given
    long currentTimestamp = System.currentTimeMillis();
    long partitionId = range(InstagramGuidGeneratorImpl.MAXIMUM_SHARD_ID);
    long sequence = range(InstagramGuidGeneratorImpl.MAXIMUM_SEQUENCE);
    long id = currentTimestamp - GuidGenerator.APPLICATION_EPOCH_TIME
      << InstagramGuidGeneratorImpl.SHARD_ID_BIT_SIZE + InstagramGuidGeneratorImpl.SEQUENCE_BIT_SIZE
      | partitionId << InstagramGuidGeneratorImpl.SEQUENCE_BIT_SIZE
      | sequence;

    // when
    InstagramGuidImpl actual = new InstagramGuidImpl(id);

    // then
    assertThat(actual.guid()).isEqualTo(id);
    assertThat(actual.timestamp()).isEqualTo(currentTimestamp);
    assertThat(actual.identifier()).isEqualTo(partitionId);
    assertThat(actual.sequence()).isEqualTo(sequence);
  }

  private long range(long maximum) {
    return ThreadLocalRandom.current().nextLong(0, maximum);
  }
}