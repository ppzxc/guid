package com.github.ppzxc.guid;

import static com.github.ppzxc.guid.GuidGenerator.APPLICATION_EPOCH_TIME;
import static com.github.ppzxc.guid.PartitionedGuidGenerator.PARTITION_ID_BIT_WISE;
import static com.github.ppzxc.guid.PartitionedGuidGenerator.SEQUENCE_BIT_SIZE;
import static com.github.ppzxc.guid.PartitionedGuidGenerator.SEQUENCE_BIT_WISE;
import static com.github.ppzxc.guid.PartitionedGuidGenerator.TIMESTAMP_BIT_SIZE;
import static com.github.ppzxc.guid.PartitionedGuidGenerator.TOTAL_BIT_SIZE;

import java.sql.Timestamp;

public record PartitionedGuid(
  long id
) implements GUID {

  @Override
  public long timestamp() {
    return (id >> TOTAL_BIT_SIZE - TIMESTAMP_BIT_SIZE) + APPLICATION_EPOCH_TIME;
  }

  @Override
  public long partitionId() {
    return (id >> SEQUENCE_BIT_SIZE) & PARTITION_ID_BIT_WISE;
  }

  @Override
  public long sequence() {
    return id & SEQUENCE_BIT_WISE;
  }

  @Override
  public String toString() {
    return "PartitionedGuid{id=%d, timestamp=%s, partitionId=%d, sequence=%d}".formatted(id,
      new Timestamp(timestamp()), partitionId(), sequence());
  }
}
