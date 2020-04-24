package calculator.input.handler;

import calculator.CalculatorContext;
import calculator.input.InputContext;

public class SkipHandler implements InputHandler {

  @Override
  public boolean match(InputContext inputCtx) {
    return true;
  }

  @Override
  public void process(InputContext inputCtx, CalculatorContext calcCtx) {
    // Skip all characters
    inputCtx.fetchNextChar();
  }
}
