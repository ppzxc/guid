package com.github.ppzxc.guid;

import static com.github.ppzxc.guid.SnowflakeGuidGenerator.MAX_NODE_ID;
import static com.github.ppzxc.guid.SnowflakeGuidGenerator.MAX_SEQUENCE;
import static com.github.ppzxc.guid.SnowflakeGuidGenerator.NODE_ID_BITS;
import static com.github.ppzxc.guid.SnowflakeGuidGenerator.SEQUENCE_BITS;
import static org.assertj.core.api.Assertions.assertThat;

import java.security.SecureRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

class SnowflakeGuidTest {

  private static SecureRandom SECURE_RANDOM;

  @BeforeAll
  static void beforeAll() {
    SECURE_RANDOM = new SecureRandom();
  }

  @DisplayName("생성된 ID 는 SnowflakeGuid 객체에서 파싱할 수 있다.")
  @RepeatedTest(100)
  void should_equals_when_given() {
    // given
    long currentTimestamp = System.currentTimeMillis();
    long nodeId = range(MAX_NODE_ID);
    long sequence = range(MAX_SEQUENCE);
    long id = currentTimestamp - GuidGenerator.APPLICATION_EPOCH_TIME << NODE_ID_BITS + SEQUENCE_BITS
      | nodeId << SEQUENCE_BITS
      | sequence;

    // when
    SnowflakeGuid actual = new SnowflakeGuid(id);

    // then
    assertThat(actual.guid()).isEqualTo(id);
    assertThat(actual.timestamp()).isEqualTo(currentTimestamp);
    assertThat(actual.identifier()).isEqualTo(nodeId);
    assertThat(actual.sequence()).isEqualTo(sequence);
  }

  private long range(long maximum) {
    return SECURE_RANDOM.nextLong(0, maximum);
  }
}