package com.github.ppzxc.guid.core;

public interface SnowflakeGuid extends Guid {

  long nodeId();

  static SnowflakeGuid of(long id) {
    return new SnowflakeGuidImpl(id);
  }
}
