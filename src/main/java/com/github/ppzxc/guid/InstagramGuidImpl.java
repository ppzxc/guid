package com.github.ppzxc.guid;

import static com.github.ppzxc.guid.GuidGenerator.APPLICATION_EPOCH_TIME;

import java.sql.Timestamp;

public class InstagramGuidImpl extends AbstractGuid {

  public InstagramGuidImpl(long id) {
    super(id, APPLICATION_EPOCH_TIME, InstagramGuidGeneratorImpl.EPOCH_BIT_SIZE, InstagramGuidGeneratorImpl.SHARD_ID_BIT_SIZE, InstagramGuidGeneratorImpl.SEQUENCE_BIT_SIZE);
  }

  @Override
  public String toString() {
    return "InstagramGuid{id=%d, timestamp=%s, shardId=%d, sequence=%d}".formatted(getId(),
      new Timestamp(timestamp()), identifier(), sequence());
  }
}
