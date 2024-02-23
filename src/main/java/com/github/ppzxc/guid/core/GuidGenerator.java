package com.github.ppzxc.guid.core;

/**
 * <pre>
 * long APPLICATION_EPOCH_TIME = 1704067200000L;
 *
 * equals
 *
 * long APPLICATION_EPOCH_TIME = ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC).toInstant().toEpochMilli();
 * </pre>
 */
public interface GuidGenerator {

  long APPLICATION_EPOCH_TIME = 1704067200000L;

  Guid next();
}
