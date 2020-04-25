package calculator.input.handler;

import calculator.CalculatorContext;
import calculator.input.InputContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class QuitHandler implements InputHandler {

  @Getter
  private final Character sign;

  @Override
  public boolean match(InputContext inputCtx) {
    final Character ch = inputCtx.getNextChar();

    if (ch == null || ch != sign) {
      return false;
    }
    
    // Remove op sign from stack
    inputCtx.fetchNextChar(); 
    return true;
  }

  @Override
  public void process(InputContext input, CalculatorContext calcCtx) {
    input.setStopped(true);
  }
}
