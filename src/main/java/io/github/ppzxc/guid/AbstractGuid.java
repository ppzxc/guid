package io.github.ppzxc.guid;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractGuid implements Guid {

  protected final long id;
  protected final long applicationEpochTime;
  protected final int timestampBitSize;
  protected final int identifierBitSize;
  protected final int sequenceBitSize;
  protected final long identifierBitWise;
  protected final long sequenceBitWise;

  protected AbstractGuid(long id, long applicationEpochTime, int epochBitSize, int identifierBitSize,
    int sequenceBitSize) {
    this.id = id;
    this.applicationEpochTime = applicationEpochTime;
    this.timestampBitSize = epochBitSize;
    this.identifierBitSize = identifierBitSize;
    this.sequenceBitSize = sequenceBitSize;
    this.identifierBitWise = Long.parseLong(joining(identifierBitSize), 2);
    this.sequenceBitWise = Long.parseLong(joining(sequenceBitSize), 2);
  }

  private String joining(int size) {
    return IntStream.range(0, size)
      .mapToObj(ignored -> "1")
      .collect(Collectors.joining());
  }

  @Override
  public long guid() {
    return id;
  }

  @Override
  public long timestamp() {
    return (id >> (identifierBitSize + sequenceBitSize)) + applicationEpochTime;
  }

  @Override
  public long identifier() {
    return (id >> sequenceBitSize) & identifierBitWise;
  }

  @Override
  public long sequence() {
    return id & sequenceBitWise;
  }
}
