package com.github.ppzxc.guid;

public abstract class AbstractIdGenerator implements IdGenerator {

  private final int timestampBitSize;
  private final int identifierBitSize;
  private final int sequenceBitSize;
  private final int totalBitSize;
  private final long maximumIdentifierId;
  private final long maximumSequence;

  private long lastTimestamp;
  private long sequence;

  protected AbstractIdGenerator(int timestampBitSize, int identifierBitSize, int sequenceBitSize, long maximumIdentifierId,
    long maximumSequence) {
    this.timestampBitSize = timestampBitSize;
    this.identifierBitSize = identifierBitSize;
    this.sequenceBitSize = sequenceBitSize;
    this.totalBitSize = timestampBitSize + identifierBitSize + sequenceBitSize;
    this.maximumIdentifierId = maximumIdentifierId;
    this.maximumSequence = maximumSequence;
    this.lastTimestamp = getCurrentTimestamp();
    this.sequence = -1L;
  }

  public abstract long getIdentifier();

  public abstract GUID create(long id);

  @Override
  public synchronized GUID next() {
    // first step: check clock moved backward
    long currentTimestamp = getCurrentTimestamp();
    if (currentTimestamp < lastTimestamp) {
      throw new IllegalStateException("Clock moved backwards.");
    }

    // second step: maximum sequence check or wait nextMillis, and save sequence
    if (currentTimestamp == lastTimestamp) {
      sequence = (sequence + 1) & maximumSequence;
      if (sequence == 0L) {
        currentTimestamp = getNextTimestamp();
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
    return create(
      currentTimestamp - APPLICATION_EPOCH_TIME << totalBitSize - timestampBitSize
        | identifier << sequenceBitSize // totalBitSize - timestampBitSize - identifierBitSize
        | sequence);
  }

  private long getNextTimestamp() {
    long newCurrentTimestamp = getCurrentTimestamp();
    while (newCurrentTimestamp <= lastTimestamp) {
      newCurrentTimestamp = getCurrentTimestamp();
    }
    return newCurrentTimestamp;
  }

  private long getCurrentTimestamp() {
    return System.currentTimeMillis();
  }
}
