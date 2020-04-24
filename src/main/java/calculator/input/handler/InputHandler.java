package calculator.input.handler;

import calculator.CalculatorContext;
import calculator.input.InputContext;

public interface InputHandler {
  boolean match(InputContext inputCtx);
  void process(InputContext inputCtx, CalculatorContext calcCtx);
}
