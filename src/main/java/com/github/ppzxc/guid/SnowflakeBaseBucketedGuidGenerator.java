package com.github.ppzxc.guid;

public final class SnowflakeBaseBucketedGuidGenerator implements BucketedGuidGenerator {

  private static final int MINUTE = 1000 * 60;
  private static final int HOUR = MINUTE * 60;
  private static final int DAY = HOUR * 24;
  private static final int DEFAULT_BUCKET_SIZE = 10;
  private final SnowflakeGuidGenerator guidGenerator;
  private final int bucketSize;

  private SnowflakeBaseBucketedGuidGenerator(SnowflakeGuidGenerator guidGenerator, int bucketSize) {
    this.guidGenerator = guidGenerator;
    this.bucketSize = bucketSize;
  }

  public static BucketedGuidGenerator of(long nodeId) {
    return new SnowflakeBaseBucketedGuidGenerator(new SnowflakeGuidGenerator(nodeId), DEFAULT_BUCKET_SIZE * DAY);
  }

  public static BucketedGuidGenerator of(long nodeId, int bucketSize) {
    return new SnowflakeBaseBucketedGuidGenerator(new SnowflakeGuidGenerator(nodeId), bucketSize);
  }

  @Override
  public BucketedGUID next() {
    return new SnowflakeBaseBucketedGUID(guidGenerator.next().guid(), bucketSize);
  }
}
