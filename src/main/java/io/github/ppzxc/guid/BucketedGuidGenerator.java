package io.github.ppzxc.guid;

/**
 * The interface Bucketed guid generator.
 */
public interface BucketedGuidGenerator extends GuidGenerator {

  BucketedGuid next();
}
