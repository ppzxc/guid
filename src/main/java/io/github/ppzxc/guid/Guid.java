package io.github.ppzxc.guid;

/**
 * The interface Guid.
 */
public interface Guid {

  /**
   * Guid long.
   *
   * @return the long
   */
  long guid();

  /**
   * Timestamp long.
   *
   * @return the long
   */
  long timestamp();

  /**
   * Identifier long.
   *
   * @return the long
   */
  long identifier();

  /**
   * Sequence long.
   *
   * @return the long
   */
  long sequence();
}
