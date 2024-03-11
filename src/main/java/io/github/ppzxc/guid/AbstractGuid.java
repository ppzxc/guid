package io.github.ppzxc.guid;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The type Abstract guid.
 */
public abstract class AbstractGuid implements Guid {

  /**
   * The Id.
   */
  protected final long id;
  /**
   * The Application epoch time.
   */
  protected final long applicationEpochTime;
  /**
   * The Timestamp bit size.
   */
  protected final int timestampBitSize;
  /**
   * The Identifier bit size.
   */
  protected final int identifierBitSize;
  /**
   * The Sequence bit size.
   */
  protected final int sequenceBitSize;
  /**
   * The Identifier bit wise.
   */
  protected final long identifierBitWise;
  /**
   * The Sequence bit wise.
   */
  protected final long sequenceBitWise;

  /**
   * Instantiates a new Abstract guid.
   *
   * @param id the id
   * @param applicationEpochTime the application epoch time
   * @param epochBitSize the epoch bit size
   * @param identifierBitSize the identifier bit size
   * @param sequenceBitSize the sequence bit size
   */
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
