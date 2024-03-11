package io.github.ppzxc.guid;

/**
 * The type Abstract guid generator.
 */
public abstract class AbstractGuidGenerator implements GuidGenerator {

  private final int epochBitSize;
  private final int identifierBitSize;
  private final int sequenceBitSize;
  private final int totalBitSize;
  private final long maximumIdentifierId;
  private final long maximumSequence;
  private final long applicationEpoch;
  private long lastTimestamp;
  private long sequence;

  /**
   * Instantiates a new Abstract guid generator.
   *
   * @param epochBitSize the epoch bit size
   * @param identifierBitSize the identifier bit size
   * @param sequenceBitSize the sequence bit size
   * @param maximumIdentifierId the maximum identifier id
   * @param maximumSequence the maximum sequence
   * @param applicationEpoch the application epoch
   */
  protected AbstractGuidGenerator(int epochBitSize, int identifierBitSize, int sequenceBitSize, long maximumIdentifierId,
    long maximumSequence, long applicationEpoch) {
    this.epochBitSize = epochBitSize;
    this.identifierBitSize = identifierBitSize;
    this.sequenceBitSize = sequenceBitSize;
    this.totalBitSize = epochBitSize + identifierBitSize + sequenceBitSize;
    this.maximumIdentifierId = maximumIdentifierId;
    this.maximumSequence = maximumSequence;
    this.applicationEpoch = applicationEpoch;
    this.lastTimestamp = -1L;
    this.sequence = -1L;
  }

  /**
   * Gets identifier.
   *
   * @return the identifier
   */
  public abstract long getIdentifier();

  /**
   * Create guid.
   *
   * @param id the id
   * @return the guid
   */
  public abstract Guid create(long id);

  @Override
  public synchronized Guid next() {
    // first step: check clock moved backward
    long currentTimestamp = getCurrentTimestamp();
    if (currentTimestamp < lastTimestamp) {
      throw new IllegalStateException("Clock moved backwards.");
    }

    // second step: maximum sequence check or wait nextMillis, and save sequence
    if (currentTimestamp == lastTimestamp) {
      sequence = (sequence + 1) & maximumSequence;
      if (sequence == 0L) {
        currentTimestamp = getNextTimestamp(currentTimestamp);
      }
    } else {
      sequence = 0L;
    }

    // third step: save timestamp
    lastTimestamp = currentTimestamp;

    // fourth step: check identifier
    long identifier = getIdentifier();
    if (identifier < 0L || identifier > maximumIdentifierId) {
      throw new IllegalArgumentException("not support range: " + identifier);
    }

    // fifth step: calculate id
    return create(currentTimestamp << totalBitSize - epochBitSize
        | identifier << sequenceBitSize // totalBitSize - timestampBitSize - identifierBitSize
        | sequence);
  }

  /**
   * Gets next timestamp.
   *
   * @param currentTimestamp the current timestamp
   * @return the next timestamp
   */
  protected long getNextTimestamp(long currentTimestamp) {
    while (currentTimestamp == lastTimestamp) {
      currentTimestamp = getCurrentTimestamp();
    }
    return currentTimestamp;
  }

  /**
   * Gets current timestamp.
   *
   * @return the current timestamp
   */
  protected long getCurrentTimestamp() {
    return System.currentTimeMillis() - applicationEpoch;
  }
}
