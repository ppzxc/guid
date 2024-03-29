package io.github.ppzxc.guid;

public interface Guid {

  long toLong();

  long epoch();

  long identifier();

  long sequence();
}
