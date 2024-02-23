package com.github.ppzxc.guid.core;

/**
 * <pre>
 *   0                   1                   2                   3
 *   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *  |U|                          Epoch                              |
 *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *  |     Epoch       |       Node Id     |         Sequence        |
 *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *   0                   1                   2                   3
 *   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 *
 *   Unused 1 bit.
 *   Epoch 41 bit.
 *   Node Id 10 bit.
 *   Sequence 12 bit.
 *  </pre>
 *
 * <a href="https://github.com/callicoder/java-snowflake/tree/master">Reference</a>
 */
public class SnowflakeGuidGeneratorImpl extends AbstractGuidGenerator {

  public static final int UNUSED_BITS = 1;
  public static final int EPOCH_BIT_SIZE = 41;
  public static final int NODE_ID_BIT_SIZE = 10;
  public static final int SEQUENCE_BIT_SIZE = 12;
  public static final int TOTAL_BITS = EPOCH_BIT_SIZE + NODE_ID_BIT_SIZE + SEQUENCE_BIT_SIZE;
  public static final long MAX_NODE_ID = (1L << NODE_ID_BIT_SIZE) - 1;
  public static final long MAX_SEQUENCE = (1L << SEQUENCE_BIT_SIZE) - 1;
  public static final long NODE_ID_BIT_WISE = Long.parseLong("1".repeat(NODE_ID_BIT_SIZE), 2);
  public static final long SEQUENCE_BIT_WISE = Long.parseLong("1".repeat(SEQUENCE_BIT_SIZE), 2);
  private final long nodeId;

  public SnowflakeGuidGeneratorImpl(long nodeId) {
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
    return new SnowflakeGuidImpl(id);
  }
}
