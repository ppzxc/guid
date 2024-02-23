package com.github.ppzxc.guid;

import static com.github.ppzxc.guid.GuidGenerator.APPLICATION_EPOCH_TIME;

import java.sql.Timestamp;

public class AutoGuid extends AbstractGuid {

  public AutoGuid(long id) {
    super(id, APPLICATION_EPOCH_TIME, SnowflakeGuidGeneratorImpl.EPOCH_BIT_SIZE, SnowflakeGuidGeneratorImpl.NODE_ID_BIT_SIZE, SnowflakeGuidGeneratorImpl.SEQUENCE_BIT_SIZE);
  }

  @Override
  public String toString() {
    return "AutoGuid{id=%d, timestamp=%s, shardId=%d, sequence=%d}".formatted(getId(),
      new Timestamp(timestamp()), identifier(), sequence());
  }
}