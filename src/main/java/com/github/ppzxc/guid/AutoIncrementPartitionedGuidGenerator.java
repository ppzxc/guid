package com.github.ppzxc.guid;

import java.util.concurrent.atomic.AtomicLong;

public final class AutoIncrementPartitionIdGenerator extends PartitionIdGenerator {

  private static final long CALIBRATION_MAX = MAXIMUM_PARTITION_ID + 1;
  private final AtomicLong autoIncrement;

  private AutoIncrementPartitionIdGenerator(AtomicLong autoIncrement) {
    this.autoIncrement = autoIncrement;
  }

  public static AutoIncrementPartitionIdGenerator zero() {
    return new AutoIncrementPartitionIdGenerator(new AtomicLong(0L));
  }

  public static AutoIncrementPartitionIdGenerator of(long initialValue) {
    return new AutoIncrementPartitionIdGenerator(new AtomicLong(initialValue));
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
