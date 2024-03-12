package io.github.ppzxc.guid;

/**
 * The type Bucketed snowflake guid.
 */
public class BucketedSnowflakeGuid extends SnowflakeGuid {

  private final long bucketSize;

  /**
   * Instantiates a new Bucketed snowflake guid.
   *
   * @param id         the id
   * @param bucketSize the bucket size
   */
  public BucketedSnowflakeGuid(long id, long bucketSize) {
    super(id);
    this.bucketSize = bucketSize;
  }

  @Override
  public String toString() {
    return "BucketedSnowflakeGuid{" +
      "id=" + id +
      ", timestamp=" + timestampBitSize +
      ", nodeId=" + identifier() +
      ", sequence=" + sequence() +
      ", bucket=" + sequence() +
      ", bucketSize=" + bucketSize +
      "} ";
  }

  /**
   * Gets bucket.
   *
   * @return the bucket
   */
  public long getBucket() {
    return timestamp() / bucketSize;
  }
}
