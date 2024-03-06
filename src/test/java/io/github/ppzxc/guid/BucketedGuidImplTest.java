package io.github.ppzxc.guid;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import io.github.ppzxc.fixh.IntUtils;
import java.sql.Timestamp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BucketedGuidImplTest {

  private static int BUCKET_ID;
  private static GuidGenerator generator;

  @BeforeAll
  static void beforeAll() {
    BUCKET_ID = IntUtils.giveMeOne(1, 512);
    generator = BucketedGuidGeneratorImpl.of(BUCKET_ID);
  }

  @Test
  void should_created() {
    assertThatCode(() -> new BucketedGuidImpl(generator.next().guid(), IntUtils.giveMeOne()))
      .doesNotThrowAnyException();
  }

  @Test
  void should_return_id() {
    long expected = generator.next().guid();
    assertThat(new BucketedGuidImpl(expected, IntUtils.giveMeOne()).guid()).isEqualTo(expected);
  }

  @Test
  void should_return_timestamp() {
    long expected = generator.next().guid();
    assertThat(new Timestamp(new BucketedGuidImpl(expected, IntUtils.giveMeOne()).timestamp()))
      .isBeforeOrEqualTo(new Timestamp(System.currentTimeMillis()));
  }

  @Test
  void should_return_identifier() {
    long expected = generator.next().guid();
    assertThat(new BucketedGuidImpl(expected, 512).sequence()).isGreaterThanOrEqualTo(0);
  }

  @Test
  void should_return_toString() {
    assertThat(new BucketedGuidImpl(generator.next().guid(), 512).toString()).isNotBlank();
  }
}