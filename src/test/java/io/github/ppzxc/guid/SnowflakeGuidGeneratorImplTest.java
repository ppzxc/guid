package io.github.ppzxc.guid;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import io.github.ppzxc.fixh.IntUtils;
import org.junit.jupiter.api.RepeatedTest;

class SnowflakeGuidGeneratorImplTest {

  @RepeatedTest(10)
  void should_throw_min_shard_id() {
    int nodeId = IntUtils.giveMeNegative();
    assertThatCode(() -> new SnowflakeGuidGeneratorImpl(nodeId))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage(String.format("NodeId must be between %d and %d", 0, nodeId));
  }

  @RepeatedTest(10)
  void should_throw_max_shard_id() {
    int nodeId = IntUtils.giveMeOne(1024, Integer.MAX_VALUE);
    assertThatCode(() -> new SnowflakeGuidGeneratorImpl(nodeId))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage(String.format("NodeId must be between %d and %d", 0, nodeId));
  }

  @RepeatedTest(10)
  void should_return_identifier() {
    int shardId = IntUtils.giveMeOne(1, 1023);
    assertThat(new SnowflakeGuidGeneratorImpl(shardId).getIdentifier()).isEqualTo(shardId);
  }

  @RepeatedTest(10)
  void should_return() {
    int shardId = IntUtils.giveMeOne(1, 1023);
    Guid guid = new SnowflakeGuidGeneratorImpl(shardId).next();
    assertThat(guid.toString()).isNotBlank();
    assertThat(guid.identifier()).isEqualTo(shardId);
  }
}