package calculator.input.handler;

import calculator.CalculatorContext;
import calculator.input.InputContext;

public class NumbersHandler implements InputHandler {

  @Override
  public boolean match(InputContext inputCtx) {
    final Character ch = inputCtx.getNextChar();

    if (ch == null) {
      return false;
    }

    if (Character.isDigit(ch)) {
      return true;
    }

    if (ch == '-') {
      final Character afterNext = inputCtx.getAfterNextChar();
      if (afterNext == null) {
        return false;
      }

      if (Character.isDigit(afterNext)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public void process(InputContext input, CalculatorContext calcCtx) {
    StringBuilder chars = new StringBuilder();

    // Handle negative values
    if (input.getNextChar() == '-') {
      chars.append(input.fetchNextChar());
    }

    while (!input.isEmpty()
        && ('.' == input.getNextChar() || Character.isDigit(input.getNextChar()))) {
      chars.append(input.fetchNextChar());
    }

    calcCtx.add((CalculatorContext cctx) -> {
      return Float.parseFloat(chars.toString());
    });
  }
}
