package com.github.ppzxc.guid;

import static com.github.ppzxc.guid.GuidGenerator.APPLICATION_EPOCH_TIME;
import static com.github.ppzxc.guid.SnowflakeGuidGenerator.NODE_ID_BITS;
import static com.github.ppzxc.guid.SnowflakeGuidGenerator.NODE_ID_BIT_WISE;
import static com.github.ppzxc.guid.SnowflakeGuidGenerator.SEQUENCE_BITS;
import static com.github.ppzxc.guid.SnowflakeGuidGenerator.SEQUENCE_BIT_WISE;

import java.sql.Timestamp;

public record SnowflakeBaseBucketedGUID(
  long id,
  int bucketSize
) implements BucketedGUID {

  @Override
  public long guid() {
    return id;
  }

  @Override
  public long timestamp() {
    return (id >> (NODE_ID_BITS + SEQUENCE_BITS)) + APPLICATION_EPOCH_TIME;
  }

  @Override
  public long identifier() {
    return (id >> SEQUENCE_BITS) & NODE_ID_BIT_WISE;
  }

  @Override
  public long sequence() {
    return id & SEQUENCE_BIT_WISE;
  }

  @Override
  public String toString() {
    return "SnowflakeBaseBucketedGUID{id=%d, timestamp=%s, nodeId=%d, sequence=%d, bucket=%d}".formatted(id,
      new Timestamp(timestamp()), identifier(), sequence(), bucket());
  }

  @Override
  public int bucket() {
    return (int) ((id >> (NODE_ID_BITS + SEQUENCE_BITS)) / bucketSize);
  }
}