package io.github.ppzxc.guid;

/**
 * <pre>
 *   0                   1                   2                   3
 *   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *  |                          Timestamp                            |
 *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *  |     Timestamp   |      Shard Id     |         Sequence        |
 *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *   0                   1                   2                   3
 *   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 *
 *   Timestamp 41 bit.
 *   - 기준 epoch 시간은 application 출시일로 고정.
 *   - (현재 시간 - 기준 epoch 시간 = 약 41년) 시간 증분 가능.
 *   - Inspired <a href="https://instagram-engineering.com/sharding-ids-at-instagram-1cf5a71e5a5c">Instagram ID</a>
 *
 *   Shard Id 10 bit.
 *   - 0 ~ 1,023 Decimal Number 를 표현 가능.
 *   - 단순 값으로는 1,024 개의 node 를 가지는 cluster 로 Sharding 할 수 있음.
 *   - 논리적인 ID 삽입할 수 있음.
 *   - shard, bucket 등 데이터를 분할 할때 사용하는 용어지만 이 전부를 포괄할 수 있는 Partition 단어 사용.
 *
 *   Sequence 13 bit.
 *   - 0 ~ 4,095 Decimal Number 를 표현 가능
 *   - 자동 증분값으로 동일한 ms (*.**1) 시간대에 생성 가능한 최대 ID는 4,096개
 *  </pre>
 */
public class InstagramGuidGeneratorImpl extends AbstractGuidGenerator {

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
  public InstagramGuidGeneratorImpl(long shardId) {
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
    return new InstagramGuidImpl(id);
  }
}
