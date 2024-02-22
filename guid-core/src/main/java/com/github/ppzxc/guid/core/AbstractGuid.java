package com.github.ppzxc.guid.core;

import java.sql.Timestamp;

public abstract class AbstractGuid implements Guid {

  private final long id;
  private final long applicationEpochTime;
  private final int timestampBitSize;
  private final int identifierBitSize;
  private final int sequenceBitSize;
  private final long identifierBitWise;
  private final long sequenceBitWise;

  public AbstractGuid(long id, long applicationEpochTime, int epochBitSize, int identifierBitSize, int sequenceBitSize) {
    this.id = id;
    this.applicationEpochTime = applicationEpochTime;
    this.timestampBitSize = epochBitSize;
    this.identifierBitSize = identifierBitSize;
    this.sequenceBitSize = sequenceBitSize;
    this.identifierBitWise = Long.parseLong("1".repeat(identifierBitSize), 2);
    this.sequenceBitWise = Long.parseLong("1".repeat(sequenceBitSize), 2);
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

  @Override
  public String toString() {
    return "AbstractGuid{id=%d, timestamp=%s, identifier=%d, sequence=%d}".formatted(id,
      new Timestamp(timestamp()), identifier(), sequence());
  }

  public long getId() {
    return id;
  }

  public long getApplicationEpochTime() {
    return applicationEpochTime;
  }

  public int getTimestampBitSize() {
    return timestampBitSize;
  }

  public int getIdentifierBitSize() {
    return identifierBitSize;
  }

  public int getSequenceBitSize() {
    return sequenceBitSize;
  }

  public long getIdentifierBitWise() {
    return identifierBitWise;
  }

  public long getSequenceBitWise() {
    return sequenceBitWise;
  }
}
