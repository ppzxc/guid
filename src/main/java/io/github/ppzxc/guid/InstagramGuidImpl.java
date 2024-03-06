package io.github.ppzxc.guid;

import static io.github.ppzxc.guid.GuidGenerator.APPLICATION_EPOCH_TIME;

import java.sql.Timestamp;

/**
 * The type Instagram guid.
 */
public class InstagramGuidImpl extends AbstractGuid {

  /**
   * Instantiates a new Instagram guid.
   *
   * @param id the id
   */
  public InstagramGuidImpl(long id) {
    super(id, APPLICATION_EPOCH_TIME, InstagramGuidGeneratorImpl.EPOCH_BIT_SIZE,
      InstagramGuidGeneratorImpl.SHARD_ID_BIT_SIZE, InstagramGuidGeneratorImpl.SEQUENCE_BIT_SIZE);
  }

  @Override
  public String toString() {
    return String.format("InstagramGuid{id=%d, timestamp=%s, shardId=%d, sequence=%d}", guid(),
      new Timestamp(timestamp()), identifier(), sequence());
  }
}
