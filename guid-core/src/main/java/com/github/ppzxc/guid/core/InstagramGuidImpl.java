package com.github.ppzxc.guid.core;

import static com.github.ppzxc.guid.core.GuidGenerator.APPLICATION_EPOCH_TIME;
import static com.github.ppzxc.guid.core.InstagramGuidGeneratorImpl.EPOCH_BIT_SIZE;
import static com.github.ppzxc.guid.core.InstagramGuidGeneratorImpl.SEQUENCE_BIT_SIZE;
import static com.github.ppzxc.guid.core.InstagramGuidGeneratorImpl.SHARD_ID_BIT_SIZE;

import java.sql.Timestamp;

public class InstagramGuidImpl extends AbstractGuid implements InstagramGuid {

  public InstagramGuidImpl(long id) {
    super(id, APPLICATION_EPOCH_TIME, EPOCH_BIT_SIZE, SHARD_ID_BIT_SIZE, SEQUENCE_BIT_SIZE);
  }

  @Override
  public String toString() {
    return "InstagramGuid{id=%d, timestamp=%s, shardId=%d, sequence=%d}".formatted(getId(),
      new Timestamp(timestamp()), identifier(), sequence());
  }

  @Override
  public long shardId() {
    return identifier();
  }
}
