package io.github.ppzxc.guid;

import static io.github.ppzxc.guid.GuidGenerator.APPLICATION_EPOCH_TIME;
import static io.github.ppzxc.guid.InstagramGuidGenerator.EPOCH_BIT_SIZE;
import static io.github.ppzxc.guid.InstagramGuidGenerator.SEQUENCE_BIT_SIZE;
import static io.github.ppzxc.guid.InstagramGuidGenerator.SHARD_ID_BIT_SIZE;

public class InstagramGuid extends AbstractGuid {

  public InstagramGuid(long id) {
    super(id, APPLICATION_EPOCH_TIME, EPOCH_BIT_SIZE, SHARD_ID_BIT_SIZE, SEQUENCE_BIT_SIZE);
  }

  @Override
  public String toString() {
    return "InstagramGuid{" +
      "id=" + id +
      ", timestamp=" + timestamp() +
      ", shardId=" + identifier() +
      ", sequence=" + sequence() +
      ", sequenceBitSize=" + sequenceBitSize +
      ", identifierBitWise=" + identifierBitWise +
      "} " + super.toString();
  }
}
