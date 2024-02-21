package com.github.ppzxc.guid;

import java.util.concurrent.atomic.AtomicLong;

public final class AutoIncrementPartitionedGuidGenerator extends PartitionedGuidGenerator {

  private static final long CALIBRATION_MAX = MAXIMUM_PARTITION_ID + 1;
  private final AtomicLong autoIncrement;

  private AutoIncrementPartitionedGuidGenerator(AtomicLong autoIncrement) {
    this.autoIncrement = autoIncrement;
  }

  public static AutoIncrementPartitionedGuidGenerator zero() {
    return new AutoIncrementPartitionedGuidGenerator(new AtomicLong(0L));
  }

  public static AutoIncrementPartitionedGuidGenerator of(long initialValue) {
    return new AutoIncrementPartitionedGuidGenerator(new AtomicLong(initialValue));
  }

  @Override
  public long getIdentifier() {
    return autoIncrement.getAndIncrement() % CALIBRATION_MAX;
  }

  @Override
  public GUID create(long id) {
    return new PartitionedGuid(id);
  }
}
