package io.github.ppzxc.guid;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.ppzxc.fixh.IntUtils;
import org.junit.jupiter.api.Test;

class AbstractGuidTest {

  @Test
  void should_created() {
    AutoIncrementSnowflakeGuidGenerator generator = AutoIncrementSnowflakeGuidGenerator.zero();
    assertThat(generator.next().toString()).isNotBlank();
  }

  @Test
  void name() {
    AutoIncrementSnowflakeGuidGenerator generator = AutoIncrementSnowflakeGuidGenerator.zero();
    for (int i = 0; i < IntUtils.giveMeOne(512, 1024); i++) {
      assertThat(generator.next().toString()).isNotBlank();
    }
  }
}