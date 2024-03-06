package io.github.ppzxc.guid;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

class AbstractGuidGeneratorNextTimestampTest {

  @Test
  void should_throw_exception_when_time_back_wards() {
    assertThatCode(() -> new TestGenerator().getNextTimestamp(-1))
      .doesNotThrowAnyException();
  }

  static class TestGenerator extends AbstractGuidGenerator {

    protected TestGenerator() {
      super(41, 10, 13, 1023, 1023, APPLICATION_EPOCH_TIME);
    }

    @Override
    public long getIdentifier() {
      return 0;
    }

    @Override
    public Guid create(long id) {
      return null;
    }
  }
}