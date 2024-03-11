package io.github.ppzxc.guid;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import io.github.ppzxc.fixh.IntUtils;
import org.junit.jupiter.api.RepeatedTest;

class BucketedSnowflakeGuidGeneratorTest {

  @RepeatedTest(10)
  void should_throw_min_shard_id() {
    int nodeId = IntUtils.giveMeNegative();
    assertThatCode(() -> new BucketedSnowflakeGuidGenerator(nodeId))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage(String.format("NodeId must be between %d and %d", 0, nodeId));
  }

  @RepeatedTest(10)
  void should_throw_max_shard_id() {
    int nodeId = IntUtils.giveMeOne(1024, Integer.MAX_VALUE);
    assertThatCode(() -> new BucketedSnowflakeGuidGenerator(nodeId))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage(String.format("NodeId must be between %d and %d", 0, nodeId));
  }

  @RepeatedTest(10)
  void should_return_identifier() {
    int nodeId = IntUtils.giveMeOne(1, 1023);
    assertThat(new BucketedSnowflakeGuidGenerator(nodeId).getIdentifier()).isEqualTo(nodeId);
  }

  @RepeatedTest(10)
  void should_return() {
    int nodeId = IntUtils.giveMeOne(1, 1023);
    Guid guid = new BucketedSnowflakeGuidGenerator(nodeId).next();
    assertThat(guid.toString()).isNotBlank();
    assertThat(guid.identifier()).isEqualTo(nodeId);
  }

  @RepeatedTest(10)
  void should_get_bucket() {
    int nodeId = IntUtils.giveMeOne(1, 1023);
    Guid guid = new BucketedSnowflakeGuidGenerator(nodeId).next();
    assertThat(guid).isInstanceOf(BucketedSnowflakeGuid.class);
    BucketedSnowflakeGuid bucketedSnowflakeGuid = (BucketedSnowflakeGuid) guid;
    assertThat(bucketedSnowflakeGuid.getBucket()).isPositive();
  }
}