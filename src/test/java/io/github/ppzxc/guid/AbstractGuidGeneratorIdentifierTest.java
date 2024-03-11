package io.github.ppzxc.guid;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

class AbstractGuidGeneratorIdentifierTest {

  @Test
  void should_throw_exception() {
    // given
    TestGenerator given = new TestGenerator();

    // when, then
    assertThatCode(given::next)
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("not support range: -2");
  }

  static class TestGenerator extends AbstractGuidGenerator {

    protected TestGenerator() {
      super(41, 10, 13, 1023, 1023, APPLICATION_EPOCH_TIME);
    }

    @Override
    public long getIdentifier() {
      return -2;
    }

    @Override
    public Guid create(long id) {
      return null;
    }
  }
}