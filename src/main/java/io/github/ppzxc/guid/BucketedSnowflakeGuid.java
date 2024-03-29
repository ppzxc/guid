package io.github.ppzxc.guid;

public class BucketedSnowflakeGuid extends SnowflakeGuid {

  private final long bucketSize;

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

  public long getBucket() {
    return epoch() / bucketSize;
  }
}
