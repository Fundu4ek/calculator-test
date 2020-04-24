package calculator.input.handler;

public interface Operation {
  /*
   *  Please note arguments order - cause arguments are located in Stack
   */
  Float apply(Float second, Float first);
}
