package io.github.ppzxc.guid;

public interface GuidGenerator {

  long APPLICATION_EPOCH_TIME = 1704067200000L;

  Guid next();
}
