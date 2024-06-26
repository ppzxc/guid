package io.github.ppzxc.guid;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutoIncrementSnowflakeGuidGeneratorTest {

  @DisplayName("첫번째 채번은 최초 디폴트 값이다.")
  @Test
  void should_default_value_when_first_next() {
    // given
    long givenTimestamp = System.currentTimeMillis();
    GuidGenerator given = AutoIncrementSnowflakeGuidGenerator.zero();

    // when
    Guid actual = given.next();

    // then
    assertThat(actual.toLong()).isNotNegative().isPositive();
    assertThat(actual.epoch()).isGreaterThanOrEqualTo(givenTimestamp);
    assertThat(actual.identifier()).isZero();
    assertThat(actual.sequence()).isZero();
  }

  @DisplayName("partition id 는 max 값에 도달하면 초기값으로 돌아간다.")
  @Test
  void should_around_partition_id_when_maximum() {
    // given
    GuidGenerator given = AutoIncrementSnowflakeGuidGenerator.of(1022);

    // when, then
    assertThat(given.next().identifier()).isEqualTo(1022);
    assertThat(given.next().identifier()).isEqualTo(1023);
    assertThat(given.next().identifier()).isZero();
  }
}