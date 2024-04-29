package io.github.ppzxc.guid;

public abstract class AbstractSimpleGuidGenerator implements GuidGenerator {

  private final int epochBitSize;
  private final int totalBitSize;
  private final long maximumSequence;
  private final long applicationEpoch;
  private long lastTimestamp;
  private long sequence;

  protected AbstractSimpleGuidGenerator(int epochBitSize, long applicationEpoch, int totalBitSize, long maximumSequence) {
    this.epochBitSize = epochBitSize;
    this.applicationEpoch = applicationEpoch;
    this.totalBitSize = totalBitSize;
    this.maximumSequence = maximumSequence;
    this.lastTimestamp = -1L;
    this.sequence = -1L;
  }

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

    // fifth step: calculate id
    return create(currentTimestamp << totalBitSize - epochBitSize
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
