package io.github.ppzxc.guid;

/**
 * The type Bucketed snowflake guid generator.
 */
public class BucketedSnowflakeGuidGenerator extends SnowflakeGuidGenerator {

  private static final long DAY = 1000L * 60L * 60L * 24L;
  private static final long DEFAULT_BUCKET = DAY * 10;
  private final long bucketSize;

  /**
   * Instantiates a new Bucketed snowflake guid generator.
   *
   * @param nodeId     the node id
   * @param bucketSize the bucket size
   */
  public BucketedSnowflakeGuidGenerator(long nodeId, long bucketSize) {
    super(nodeId);
    this.bucketSize = bucketSize;
  }

  /**
   * Instantiates a new Bucketed snowflake guid generator.
   *
   * @param nodeId the node id
   */
  public BucketedSnowflakeGuidGenerator(long nodeId) {
    this(nodeId, DEFAULT_BUCKET);
  }

  @Override
  public Guid create(long id) {
    return new BucketedSnowflakeGuid(id, bucketSize);
  }
}
