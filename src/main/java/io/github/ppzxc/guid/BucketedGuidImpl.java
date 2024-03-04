package io.github.ppzxc.guid;

import static io.github.ppzxc.guid.GuidGenerator.APPLICATION_EPOCH_TIME;
import static io.github.ppzxc.guid.SnowflakeGuidGeneratorImpl.NODE_ID_BIT_SIZE;
import static io.github.ppzxc.guid.SnowflakeGuidGeneratorImpl.NODE_ID_BIT_WISE;
import static io.github.ppzxc.guid.SnowflakeGuidGeneratorImpl.SEQUENCE_BIT_SIZE;
import static io.github.ppzxc.guid.SnowflakeGuidGeneratorImpl.SEQUENCE_BIT_WISE;

import java.sql.Timestamp;

/**
 * The type Bucketed guid.
 */
public class BucketedGuidImpl implements BucketedGuid {

  private final long id;
  private final int bucketSize;

  /**
   * Instantiates a new Bucketed guid.
   *
   * @param id         the id
   * @param bucketSize the bucket size
   */
  public BucketedGuidImpl(long id, int bucketSize) {
    this.id = id;
    this.bucketSize = bucketSize;
  }

  @Override
  public long guid() {
    return id;
  }

  @Override
  public long timestamp() {
    return (id >> (NODE_ID_BIT_SIZE + SEQUENCE_BIT_SIZE)) + APPLICATION_EPOCH_TIME;
  }

  @Override
  public long identifier() {
    return (id >> SEQUENCE_BIT_SIZE) & NODE_ID_BIT_WISE;
  }

  @Override
  public long sequence() {
    return id & SEQUENCE_BIT_WISE;
  }

  @Override
  public String toString() {
    return String.format("SnowflakeBaseBucketedGUID{id=%d, timestamp=%s, nodeId=%d, sequence=%d, bucket=%d}", id,
      new Timestamp(timestamp()), identifier(), sequence(), bucket());
  }

  @Override
  public int bucket() {
    return (int) ((id >> (NODE_ID_BIT_SIZE + SEQUENCE_BIT_SIZE)) / bucketSize);
  }
}