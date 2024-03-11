package io.github.ppzxc.guid;

/**
 * The type Snowflake guid generator.
 */
public class SnowflakeGuidGenerator extends AbstractGuidGenerator {

  /**
   * The constant EPOCH_BIT_SIZE.
   */
  public static final int EPOCH_BIT_SIZE = 41;
  /**
   * The constant NODE_ID_BIT_SIZE.
   */
  public static final int NODE_ID_BIT_SIZE = 10;
  /**
   * The constant SEQUENCE_BIT_SIZE.
   */
  public static final int SEQUENCE_BIT_SIZE = 12;
  /**
   * The constant MAX_NODE_ID.
   */
  public static final long MAX_NODE_ID = (1L << NODE_ID_BIT_SIZE) - 1;
  /**
   * The constant MAX_SEQUENCE.
   */
  public static final long MAX_SEQUENCE = (1L << SEQUENCE_BIT_SIZE) - 1;
  private final long nodeId;

  /**
   * Instantiates a new Snowflake guid generator.
   *
   * @param nodeId the node id
   */
  public SnowflakeGuidGenerator(long nodeId) {
    super(EPOCH_BIT_SIZE, NODE_ID_BIT_SIZE, SEQUENCE_BIT_SIZE, MAX_NODE_ID, MAX_SEQUENCE, APPLICATION_EPOCH_TIME);
    if (nodeId < 0 || nodeId > MAX_NODE_ID) {
      throw new IllegalArgumentException(String.format("NodeId must be between %d and %d", 0, nodeId));
    }
    this.nodeId = nodeId;
  }

  @Override
  public long getIdentifier() {
    return nodeId;
  }

  @Override
  public Guid create(long id) {
    return new SnowflakeGuid(id);
  }
}
