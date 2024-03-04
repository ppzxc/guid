package io.github.ppzxc.guid;

import static io.github.ppzxc.guid.GuidGenerator.APPLICATION_EPOCH_TIME;
import static io.github.ppzxc.guid.SnowflakeGuidGeneratorImpl.EPOCH_BIT_SIZE;
import static io.github.ppzxc.guid.SnowflakeGuidGeneratorImpl.NODE_ID_BIT_SIZE;
import static io.github.ppzxc.guid.SnowflakeGuidGeneratorImpl.SEQUENCE_BIT_SIZE;

import java.sql.Timestamp;

/**
 * The type Snowflake guid.
 */
class SnowflakeGuidImpl extends AbstractGuid {

  /**
   * Instantiates a new Snowflake guid.
   *
   * @param id the id
   */
  SnowflakeGuidImpl(long id) {
    super(id, APPLICATION_EPOCH_TIME, EPOCH_BIT_SIZE, NODE_ID_BIT_SIZE, SEQUENCE_BIT_SIZE);
  }

  @Override
  public String toString() {
    return "SnowflakeGuid{id=%d, timestamp=%s, nodeId=%d, sequence=%d}".formatted(guid(), new Timestamp(timestamp()),
      identifier(), sequence());
  }
}
