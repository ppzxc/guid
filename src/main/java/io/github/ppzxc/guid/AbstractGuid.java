package io.github.ppzxc.guid;

import java.sql.Timestamp;

/**
 * The type Abstract guid.
 */
public abstract class AbstractGuid implements Guid {

  private final long id;
  private final long applicationEpochTime;
  private final int timestampBitSize;
  private final int identifierBitSize;
  private final int sequenceBitSize;
  private final long identifierBitWise;
  private final long sequenceBitWise;

  /**
   * Instantiates a new Abstract guid.
   *
   * @param id                   the id
   * @param applicationEpochTime the application epoch time
   * @param epochBitSize         the epoch bit size
   * @param identifierBitSize    the identifier bit size
   * @param sequenceBitSize      the sequence bit size
   */
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

  /**
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Gets application epoch time.
   *
   * @return the application epoch time
   */
  public long getApplicationEpochTime() {
    return applicationEpochTime;
  }

  /**
   * Gets timestamp bit size.
   *
   * @return the timestamp bit size
   */
  public int getTimestampBitSize() {
    return timestampBitSize;
  }

  /**
   * Gets identifier bit size.
   *
   * @return the identifier bit size
   */
  public int getIdentifierBitSize() {
    return identifierBitSize;
  }

  /**
   * Gets sequence bit size.
   *
   * @return the sequence bit size
   */
  public int getSequenceBitSize() {
    return sequenceBitSize;
  }

  /**
   * Gets identifier bit wise.
   *
   * @return the identifier bit wise
   */
  public long getIdentifierBitWise() {
    return identifierBitWise;
  }

  /**
   * Gets sequence bit wise.
   *
   * @return the sequence bit wise
   */
  public long getSequenceBitWise() {
    return sequenceBitWise;
  }
}
