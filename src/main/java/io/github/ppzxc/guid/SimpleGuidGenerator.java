package io.github.ppzxc.guid;

public class SimpleGuidGenerator extends AbstractSimpleGuidGenerator {

  public static final int EPOCH_BIT_SIZE = 41;
  public static final int SEQUENCE_BIT_SIZE = 22;
  public static final long MAX_SEQUENCE = (1L << SEQUENCE_BIT_SIZE) - 1;

  public SimpleGuidGenerator() {
    super(EPOCH_BIT_SIZE, APPLICATION_EPOCH_TIME, EPOCH_BIT_SIZE + SEQUENCE_BIT_SIZE, MAX_SEQUENCE);
  }

  @Override
  public Guid create(long id) {
    return new SimpleGuid(id);
  }
}
