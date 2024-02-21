package com.github.ppzxc.guid;

import static com.github.ppzxc.guid.PartitionedGuidGenerator.MAXIMUM_PARTITION_ID;
import static com.github.ppzxc.guid.PartitionedGuidGenerator.MAXIMUM_SEQUENCE;
import static com.github.ppzxc.guid.PartitionedGuidGenerator.PARTITION_ID_BIT_SIZE;
import static com.github.ppzxc.guid.PartitionedGuidGenerator.SEQUENCE_BIT_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

import java.security.SecureRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

class PartitionedGuidTest {

  private static SecureRandom SECURE_RANDOM;

  @BeforeAll
  static void beforeAll() {
    SECURE_RANDOM = new SecureRandom();
  }

  @DisplayName("생성된 ID 는 PartitionGuid 객체에서 파싱할 수 있다.")
  @RepeatedTest(100)
  void should_equals_when_given() {
    // given
    long currentTimestamp = System.currentTimeMillis();
    long partitionId = range(MAXIMUM_PARTITION_ID);
    long sequence = range(MAXIMUM_SEQUENCE);
    long id = currentTimestamp - GuidGenerator.APPLICATION_EPOCH_TIME
      << PARTITION_ID_BIT_SIZE + SEQUENCE_BIT_SIZE
      | partitionId << SEQUENCE_BIT_SIZE
      | sequence;

    // when
    PartitionedGuid actual = new PartitionedGuid(id);

    // then
    assertThat(actual.guid()).isEqualTo(id);
    assertThat(actual.timestamp()).isEqualTo(currentTimestamp);
    assertThat(actual.identifier()).isEqualTo(partitionId);
    assertThat(actual.sequence()).isEqualTo(sequence);
  }

  private long range(long maximum) {
    return SECURE_RANDOM.nextLong(0, maximum);
  }
}