package com.github.ppzxc.guid.core;

import static com.github.ppzxc.guid.core.SnowflakeGuidGeneratorImpl.EPOCH_BIT_SIZE;
import static com.github.ppzxc.guid.core.SnowflakeGuidGeneratorImpl.MAX_NODE_ID;
import static com.github.ppzxc.guid.core.SnowflakeGuidGeneratorImpl.MAX_SEQUENCE;
import static com.github.ppzxc.guid.core.SnowflakeGuidGeneratorImpl.NODE_ID_BIT_SIZE;
import static com.github.ppzxc.guid.core.SnowflakeGuidGeneratorImpl.SEQUENCE_BIT_SIZE;

import java.util.concurrent.atomic.AtomicLong;

public final class AutoGuidGeneratorImpl extends AbstractGuidGenerator implements GuidGenerator {

  private static final long CALIBRATION_MAX = MAX_NODE_ID + 1;
  private final AtomicLong autoIncrement;

  private AutoGuidGeneratorImpl(AtomicLong autoIncrement) {
    super(EPOCH_BIT_SIZE, NODE_ID_BIT_SIZE, SEQUENCE_BIT_SIZE, MAX_NODE_ID, MAX_SEQUENCE, APPLICATION_EPOCH_TIME);
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
