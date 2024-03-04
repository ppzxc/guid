package io.github.ppzxc.guid;

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

  /**
   * The constant APPLICATION_EPOCH_TIME.
   */
  long APPLICATION_EPOCH_TIME = 1704067200000L;

  /**
   * Next guid.
   *
   * @return the guid
   */
  Guid next();
}
