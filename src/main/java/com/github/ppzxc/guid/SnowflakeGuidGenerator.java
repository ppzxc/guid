package com.github.ppzxc.guid;

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
public class SnowflakeGuidGenerator extends AbstractGuidGenerator {

  public static final int UNUSED_BITS = 1;
  public static final int EPOCH_BITS = 41;
  public static final int NODE_ID_BITS = 10;
  public static final int SEQUENCE_BITS = 12;
  public static final int TOTAL_BITS = EPOCH_BITS + NODE_ID_BITS + SEQUENCE_BITS;
  public static final long MAX_NODE_ID = (1L << NODE_ID_BITS) - 1;
  public static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;
  public static final long NODE_ID_BIT_WISE = Long.parseLong("1".repeat(NODE_ID_BITS), 2);
  public static final long SEQUENCE_BIT_WISE = Long.parseLong("1".repeat(SEQUENCE_BITS), 2);
  private final long nodeId;

  public SnowflakeGuidGenerator(long nodeId) {
    super(EPOCH_BITS, NODE_ID_BITS, SEQUENCE_BITS, MAX_NODE_ID, MAX_SEQUENCE, APPLICATION_EPOCH_TIME);
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
  public GUID create(long id) {
    return new SnowflakeGuid(id);
  }
}
