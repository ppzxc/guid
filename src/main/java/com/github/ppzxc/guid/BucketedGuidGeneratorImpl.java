package com.github.ppzxc.guid;

public class BucketedGuidGeneratorImpl implements BucketedGuidGenerator {

  private static final int MINUTE = 1000 * 60;
  private static final int HOUR = MINUTE * 60;
  private static final int DAY = HOUR * 24;
  public static final int DEFAULT_BUCKET_SIZE = 10 * DAY;
  private final SnowflakeGuidGeneratorImpl guidGenerator;
  private final int bucketSize;

  public BucketedGuidGeneratorImpl(SnowflakeGuidGeneratorImpl guidGenerator, int bucketSize) {
    this.guidGenerator = guidGenerator;
    this.bucketSize = bucketSize;
  }

  public static BucketedGuidGenerator of(SnowflakeGuidGeneratorImpl snowflakeGuidGeneratorImpl) {
    return new BucketedGuidGeneratorImpl(snowflakeGuidGeneratorImpl, DEFAULT_BUCKET_SIZE);
  }

  public static BucketedGuidGenerator of(SnowflakeGuidGeneratorImpl snowflakeGuidGeneratorImpl, int bucketSize) {
    return new BucketedGuidGeneratorImpl(snowflakeGuidGeneratorImpl, bucketSize);
  }

  public static BucketedGuidGenerator of(long nodeId) {
    return new BucketedGuidGeneratorImpl(new SnowflakeGuidGeneratorImpl(nodeId), DEFAULT_BUCKET_SIZE);
  }

  public static BucketedGuidGenerator of(long nodeId, int bucketSize) {
    return new BucketedGuidGeneratorImpl(new SnowflakeGuidGeneratorImpl(nodeId), bucketSize);
  }

  @Override
  public BucketedGuid next() {
    return new BucketedGuidImpl(guidGenerator.next().guid(), bucketSize);
  }
}
