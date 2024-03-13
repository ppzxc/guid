package io.github.ppzxc.guid;

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

  public abstract long getIdentifier();

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

  protected long getNextTimestamp(long currentTimestamp) {
    while (currentTimestamp == lastTimestamp) {
      currentTimestamp = getCurrentTimestamp();
    }
    return currentTimestamp;
  }

  protected long getCurrentTimestamp() {
    return System.currentTimeMillis() - applicationEpoch;
  }
}
