package io.github.ppzxc.guid;

/**
 * The type Instagram guid generator.
 */
public class InstagramGuidGenerator extends AbstractGuidGenerator {

  /**
   * The constant EPOCH_BIT_SIZE.
   */
  public static final int EPOCH_BIT_SIZE = 41;
  /**
   * The constant SHARD_ID_BIT_SIZE.
   */
  public static final int SHARD_ID_BIT_SIZE = 10;
  /**
   * The constant SEQUENCE_BIT_SIZE.
   */
  public static final int SEQUENCE_BIT_SIZE = 13;
  /**
   * The constant MAXIMUM_SHARD_ID.
   */
  public static final long MAXIMUM_SHARD_ID = ~(-1L << SHARD_ID_BIT_SIZE);
  /**
   * The constant MAXIMUM_SEQUENCE.
   */
  public static final long MAXIMUM_SEQUENCE = ~(-1L << SEQUENCE_BIT_SIZE);
  private final long shardId;

  /**
   * Instantiates a new Instagram guid generator.
   *
   * @param shardId the shard id
   */
  public InstagramGuidGenerator(long shardId) {
    super(EPOCH_BIT_SIZE, SHARD_ID_BIT_SIZE, SEQUENCE_BIT_SIZE, MAXIMUM_SHARD_ID, MAXIMUM_SEQUENCE,
      APPLICATION_EPOCH_TIME);
    if (shardId < 0 || shardId > MAXIMUM_SHARD_ID) {
      throw new IllegalArgumentException(String.format("NodeId must be between %d and %d", 0, shardId));
    }
    this.shardId = shardId;
  }

  @Override
  public long getIdentifier() {
    return shardId;
  }

  @Override
  public Guid create(long id) {
    return new InstagramGuid(id);
  }
}
