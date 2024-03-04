package io.github.ppzxc.guid;

import java.util.concurrent.atomic.AtomicLong;

/**
 * The type Auto guid generator.
 */
public final class AutoGuidGeneratorImpl extends AbstractGuidGenerator implements GuidGenerator {

  private static final long CALIBRATION_MAX = SnowflakeGuidGeneratorImpl.MAX_NODE_ID + 1;
  private final AtomicLong autoIncrement;

  private AutoGuidGeneratorImpl(AtomicLong autoIncrement) {
    super(SnowflakeGuidGeneratorImpl.EPOCH_BIT_SIZE, SnowflakeGuidGeneratorImpl.NODE_ID_BIT_SIZE, SnowflakeGuidGeneratorImpl.SEQUENCE_BIT_SIZE, SnowflakeGuidGeneratorImpl.MAX_NODE_ID, SnowflakeGuidGeneratorImpl.MAX_SEQUENCE, APPLICATION_EPOCH_TIME);
    this.autoIncrement = autoIncrement;
  }

  /**
   * Zero auto guid generator.
   *
   * @return the auto guid generator
   */
  public static AutoGuidGeneratorImpl zero() {
    return new AutoGuidGeneratorImpl(new AtomicLong(0L));
  }

  /**
   * Of auto guid generator.
   *
   * @param initialValue the initial value
   * @return the auto guid generator
   */
  public static AutoGuidGeneratorImpl of(long initialValue) {
    return new AutoGuidGeneratorImpl(new AtomicLong(initialValue));
  }

  @Override
  public long getIdentifier() {
    return autoIncrement.getAndIncrement() % CALIBRATION_MAX;
  }

  @Override
  public Guid create(long id) {
    return new AutoGuid(id);
  }
}
