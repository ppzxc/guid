package com.github.ppzxc.guid.core;

public interface InstagramGuid extends Guid {

  long shardId();

  static InstagramGuid of(long id) {
    return new InstagramGuidImpl(id);
  }
}
