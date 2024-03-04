package io.github.ppzxc.guid;

/**
 * The type Bucketed guid generator.
 */
public class BucketedGuidGeneratorImpl implements BucketedGuidGenerator {

  private static final int MINUTE = 1000 * 60;
  private static final int HOUR = MINUTE * 60;
  private static final int DAY = HOUR * 24;
  /**
   * The constant DEFAULT_BUCKET_SIZE.
   */
  public static final int DEFAULT_BUCKET_SIZE = 10 * DAY;
  private final SnowflakeGuidGeneratorImpl guidGenerator;
  private final int bucketSize;

  /**
   * Instantiates a new Bucketed guid generator.
   *
   * @param guidGenerator the guid generator
   * @param bucketSize    the bucket size
   */
  public BucketedGuidGeneratorImpl(SnowflakeGuidGeneratorImpl guidGenerator, int bucketSize) {
    this.guidGenerator = guidGenerator;
    this.bucketSize = bucketSize;
  }

  /**
   * Of bucketed guid generator.
   *
   * @param snowflakeGuidGeneratorImpl the snowflake guid generator
   * @return the bucketed guid generator
   */
  public static BucketedGuidGenerator of(SnowflakeGuidGeneratorImpl snowflakeGuidGeneratorImpl) {
    return new BucketedGuidGeneratorImpl(snowflakeGuidGeneratorImpl, DEFAULT_BUCKET_SIZE);
  }

  /**
   * Of bucketed guid generator.
   *
   * @param snowflakeGuidGeneratorImpl the snowflake guid generator
   * @param bucketSize                 the bucket size
   * @return the bucketed guid generator
   */
  public static BucketedGuidGenerator of(SnowflakeGuidGeneratorImpl snowflakeGuidGeneratorImpl, int bucketSize) {
    return new BucketedGuidGeneratorImpl(snowflakeGuidGeneratorImpl, bucketSize);
  }

  /**
   * Of bucketed guid generator.
   *
   * @param nodeId the node id
   * @return the bucketed guid generator
   */
  public static BucketedGuidGenerator of(long nodeId) {
    return new BucketedGuidGeneratorImpl(new SnowflakeGuidGeneratorImpl(nodeId), DEFAULT_BUCKET_SIZE);
  }

  /**
   * Of bucketed guid generator.
   *
   * @param nodeId     the node id
   * @param bucketSize the bucket size
   * @return the bucketed guid generator
   */
  public static BucketedGuidGenerator of(long nodeId, int bucketSize) {
    return new BucketedGuidGeneratorImpl(new SnowflakeGuidGeneratorImpl(nodeId), bucketSize);
  }

  @Override
  public BucketedGuid next() {
    return new BucketedGuidImpl(guidGenerator.next().guid(), bucketSize);
  }
}
