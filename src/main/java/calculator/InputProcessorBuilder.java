package calculator;

import calculator.input.InputContext;
import calculator.input.InputProcessor;
import calculator.input.handler.MinusOperationHandler;
import calculator.input.handler.NumbersHandler;
import calculator.input.handler.QuitHandler;
import calculator.input.handler.SimpleOperationHandler;
import calculator.input.handler.SkipHandler;

public class InputProcessorBuilder {
  public InputProcessor build() {

    InputProcessor processor = new InputProcessor();

    processor.addHandler(new NumbersHandler());
    processor.addHandler(new SimpleOperationHandler('*', (Float second, Float first) -> {
      return first * second;
    }));
    processor.addHandler(new SimpleOperationHandler('+', (Float second, Float first) -> {
      return first + second;
    }));
    processor.addHandler(new SimpleOperationHandler('/', (Float second, Float first) -> {
      return first / second;
    }));
    processor.addHandler(new MinusOperationHandler('-', (Float second, Float first) -> {
      return first - second;
    }));
    processor.addHandler(new QuitHandler(InputContext.COMMAND_QUIT_CHAR));
    processor.addHandler(new SkipHandler());

    return processor;
  }
}
