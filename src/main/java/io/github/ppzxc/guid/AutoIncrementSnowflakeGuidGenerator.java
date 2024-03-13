package io.github.ppzxc.guid;

import static io.github.ppzxc.guid.SnowflakeGuidGenerator.EPOCH_BIT_SIZE;
import static io.github.ppzxc.guid.SnowflakeGuidGenerator.MAX_NODE_ID;
import static io.github.ppzxc.guid.SnowflakeGuidGenerator.MAX_SEQUENCE;
import static io.github.ppzxc.guid.SnowflakeGuidGenerator.NODE_ID_BIT_SIZE;
import static io.github.ppzxc.guid.SnowflakeGuidGenerator.SEQUENCE_BIT_SIZE;

import java.util.concurrent.atomic.AtomicLong;

public final class AutoIncrementSnowflakeGuidGenerator extends AbstractGuidGenerator implements GuidGenerator {

  private static final long CALIBRATION_MAX = MAX_NODE_ID + 1;
  private final AtomicLong autoIncrement;

  private AutoIncrementSnowflakeGuidGenerator(AtomicLong autoIncrement) {
    super(EPOCH_BIT_SIZE, NODE_ID_BIT_SIZE, SEQUENCE_BIT_SIZE, MAX_NODE_ID, MAX_SEQUENCE, APPLICATION_EPOCH_TIME);
    this.autoIncrement = autoIncrement;
  }

  public static AutoIncrementSnowflakeGuidGenerator zero() {
    return new AutoIncrementSnowflakeGuidGenerator(new AtomicLong(0L));
  }

  public static AutoIncrementSnowflakeGuidGenerator of(long initialValue) {
    return new AutoIncrementSnowflakeGuidGenerator(new AtomicLong(initialValue));
  }

  @Override
  public long getIdentifier() {
    return autoIncrement.getAndIncrement() % CALIBRATION_MAX;
  }

  @Override
  public Guid create(long id) {
    return new SnowflakeGuid(id);
  }
}
