package com.github.ppzxc.guid;

import static com.github.ppzxc.guid.GuidGenerator.APPLICATION_EPOCH_TIME;
import static com.github.ppzxc.guid.SnowflakeGuidGenerator.NODE_ID_BITS;
import static com.github.ppzxc.guid.SnowflakeGuidGenerator.NODE_ID_BIT_WISE;
import static com.github.ppzxc.guid.SnowflakeGuidGenerator.SEQUENCE_BITS;
import static com.github.ppzxc.guid.SnowflakeGuidGenerator.SEQUENCE_BIT_WISE;

import java.sql.Timestamp;

public record SnowflakeGuid(
  long id
) implements GUID {

  @Override
  public long timestamp() {
    return (id >> (NODE_ID_BITS + SEQUENCE_BITS)) + APPLICATION_EPOCH_TIME;
  }

  @Override
  public long partitionId() {
    return (id >> SEQUENCE_BITS) & NODE_ID_BIT_WISE;
  }

  @Override
  public long sequence() {
    return id & SEQUENCE_BIT_WISE;
  }

  @Override
  public String toString() {
    return "SnowflakeGuid{id=%d, timestamp=%s, nodeId=%d, sequence=%d}".formatted(id,
      new Timestamp(timestamp()), partitionId(), sequence());
  }
}
