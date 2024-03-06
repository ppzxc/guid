package io.github.ppzxc.guid;

import static io.github.ppzxc.guid.SnowflakeGuidGeneratorImpl.MAX_NODE_ID;
import static io.github.ppzxc.guid.SnowflakeGuidGeneratorImpl.MAX_SEQUENCE;
import static io.github.ppzxc.guid.SnowflakeGuidGeneratorImpl.NODE_ID_BIT_SIZE;
import static io.github.ppzxc.guid.SnowflakeGuidGeneratorImpl.SEQUENCE_BIT_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

class SnowflakeGuidImplTest {

  @DisplayName("생성된 ID 는 SnowflakeGuid 객체에서 파싱할 수 있다.")
  @RepeatedTest(10)
  void should_equals_when_given() {
    // given
    long currentTimestamp = System.currentTimeMillis();
    long nodeId = range(MAX_NODE_ID);
    long sequence = range(MAX_SEQUENCE);
    long id = currentTimestamp - GuidGenerator.APPLICATION_EPOCH_TIME << NODE_ID_BIT_SIZE + SEQUENCE_BIT_SIZE
      | nodeId << SEQUENCE_BIT_SIZE
      | sequence;

    // when
    SnowflakeGuidImpl actual = new SnowflakeGuidImpl(id);

    // then
    assertThat(actual.guid()).isEqualTo(id);
    assertThat(actual.timestamp()).isEqualTo(currentTimestamp);
    assertThat(actual.identifier()).isEqualTo(nodeId);
    assertThat(actual.sequence()).isEqualTo(sequence);
  }

  private long range(long maximum) {
    return ThreadLocalRandom.current().nextLong(0, maximum);
  }
}