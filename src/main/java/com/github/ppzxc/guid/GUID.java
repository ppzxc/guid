package com.github.ppzxc.guid;

public interface GUID {

  long id();

  long timestamp();

  long partitionId();

  long sequence();
}
