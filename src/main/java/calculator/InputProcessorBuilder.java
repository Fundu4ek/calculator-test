package calculator;

import calculator.input.InputContext;
import calculator.input.InputProcessor;
import calculator.input.handler.MinusOperationHandler;
import calculator.input.handler.NumbersHandler;
import calculator.input.handler.SimpleOperationHandler;

public class InputProcessorBuilder {
  public InputProcessor build(InputContext inputCtx, CalculatorContext calcCtx) {

    InputProcessor processor = new InputProcessor(inputCtx, calcCtx);

    processor.addHandler(new NumbersHandler());
    processor.addHandler(new SimpleOperationHandler('*', (Float second, Float first) -> { return first * second; } ));
    processor.addHandler(new SimpleOperationHandler('+', (Float second, Float first) -> { return first + second; } ));
    processor.addHandler(new SimpleOperationHandler('/', (Float second, Float first) -> { return first / second; } ));
    processor.addHandler(new MinusOperationHandler('-', (Float second, Float first) -> { return first - second; } ));

    return processor;
  }
}
