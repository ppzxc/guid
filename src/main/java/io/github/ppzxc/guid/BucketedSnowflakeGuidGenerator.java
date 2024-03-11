package io.github.ppzxc.guid;

/**
 * The type Bucketed snowflake guid generator.
 */
public class BucketedSnowflakeGuidGenerator extends SnowflakeGuidGenerator {

  /**
   * Instantiates a new Bucketed snowflake guid generator.
   *
   * @param nodeId the node id
   */
  public BucketedSnowflakeGuidGenerator(long nodeId) {
    super(nodeId);
  }

  @Override
  public Guid create(long id) {
    return new BucketedSnowflakeGuid(id);
  }
}
