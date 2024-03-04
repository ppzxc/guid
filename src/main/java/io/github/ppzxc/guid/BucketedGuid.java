package io.github.ppzxc.guid;

/**
 * The interface Bucketed guid.
 */
public interface BucketedGuid extends Guid {

  /**
   * Bucket int.
   *
   * @return the int
   */
  int bucket();
}
