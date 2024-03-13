package io.github.ppzxc.guid;

public class BucketedSnowflakeGuidGenerator extends SnowflakeGuidGenerator {

  private static final long DAY = 1000L * 60L * 60L * 24L;
  private static final long DEFAULT_BUCKET = DAY * 10;
  private final long bucketSize;

  public BucketedSnowflakeGuidGenerator(long nodeId, long bucketSize) {
    super(nodeId);
    this.bucketSize = bucketSize;
  }

  public BucketedSnowflakeGuidGenerator(long nodeId) {
    this(nodeId, DEFAULT_BUCKET);
  }

  @Override
  public Guid create(long id) {
    return new BucketedSnowflakeGuid(id, bucketSize);
  }
}
