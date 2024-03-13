package io.github.ppzxc.guid;

public class InstagramGuidGenerator extends AbstractGuidGenerator {

  public static final int EPOCH_BIT_SIZE = 41;
  public static final int SHARD_ID_BIT_SIZE = 10;
  public static final int SEQUENCE_BIT_SIZE = 13;
  public static final long MAXIMUM_SHARD_ID = ~(-1L << SHARD_ID_BIT_SIZE);
  public static final long MAXIMUM_SEQUENCE = ~(-1L << SEQUENCE_BIT_SIZE);
  private final long shardId;

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
