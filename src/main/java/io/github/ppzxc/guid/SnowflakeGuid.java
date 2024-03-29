package io.github.ppzxc.guid;

import static io.github.ppzxc.guid.GuidGenerator.APPLICATION_EPOCH_TIME;
import static io.github.ppzxc.guid.SnowflakeGuidGenerator.EPOCH_BIT_SIZE;
import static io.github.ppzxc.guid.SnowflakeGuidGenerator.NODE_ID_BIT_SIZE;
import static io.github.ppzxc.guid.SnowflakeGuidGenerator.SEQUENCE_BIT_SIZE;

public class SnowflakeGuid extends AbstractGuid {

  public SnowflakeGuid(long id) {
    super(id, APPLICATION_EPOCH_TIME, EPOCH_BIT_SIZE, NODE_ID_BIT_SIZE, SEQUENCE_BIT_SIZE);
  }

  @Override
  public String toString() {
    return "SnowflakeGuid{" +
      "id=" + id +
      ", epoch=" + timestampBitSize +
      ", nodeId=" + identifier() +
      ", sequence=" + sequence() +
      "} ";
  }
}
