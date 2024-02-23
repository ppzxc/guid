package com.github.ppzxc.guid;

import java.util.concurrent.atomic.AtomicLong;

public final class AutoGuidGeneratorImpl extends AbstractGuidGenerator implements GuidGenerator {

  private static final long CALIBRATION_MAX = SnowflakeGuidGeneratorImpl.MAX_NODE_ID + 1;
  private final AtomicLong autoIncrement;

  private AutoGuidGeneratorImpl(AtomicLong autoIncrement) {
    super(SnowflakeGuidGeneratorImpl.EPOCH_BIT_SIZE, SnowflakeGuidGeneratorImpl.NODE_ID_BIT_SIZE, SnowflakeGuidGeneratorImpl.SEQUENCE_BIT_SIZE, SnowflakeGuidGeneratorImpl.MAX_NODE_ID, SnowflakeGuidGeneratorImpl.MAX_SEQUENCE, APPLICATION_EPOCH_TIME);
    this.autoIncrement = autoIncrement;
  }

  public static AutoGuidGeneratorImpl zero() {
    return new AutoGuidGeneratorImpl(new AtomicLong(0L));
  }

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
