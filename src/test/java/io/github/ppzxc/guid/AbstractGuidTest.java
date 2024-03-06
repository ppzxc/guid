package io.github.ppzxc.guid;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.ppzxc.fixh.IntUtils;
import org.junit.jupiter.api.Test;

class AbstractGuidTest {

  @Test
  void should_created() {
    AutoGuidGeneratorImpl generator = AutoGuidGeneratorImpl.zero();
    assertThat(generator.next().toString()).isNotBlank();
  }

  @Test
  void name() {
    AutoGuidGeneratorImpl generator = AutoGuidGeneratorImpl.zero();
    for (int i = 0; i < IntUtils.giveMeOne(512, 1024); i++) {
      assertThat(generator.next().toString()).isNotBlank();
    }
  }
}