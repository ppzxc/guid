package io.github.ppzxc.guid;

import static io.github.ppzxc.guid.GuidGenerator.APPLICATION_EPOCH_TIME;

import java.sql.Timestamp;

/**
 * The type Auto guid.
 */
public class AutoGuid extends AbstractGuid {

  /**
   * Instantiates a new Auto guid.
   *
   * @param id the id
   */
  public AutoGuid(long id) {
    super(id, APPLICATION_EPOCH_TIME, SnowflakeGuidGeneratorImpl.EPOCH_BIT_SIZE, SnowflakeGuidGeneratorImpl.NODE_ID_BIT_SIZE, SnowflakeGuidGeneratorImpl.SEQUENCE_BIT_SIZE);
  }

  @Override
  public String toString() {
    return "AutoGuid{id=%d, timestamp=%s, shardId=%d, sequence=%d}".formatted(getId(),
      new Timestamp(timestamp()), identifier(), sequence());
  }
}