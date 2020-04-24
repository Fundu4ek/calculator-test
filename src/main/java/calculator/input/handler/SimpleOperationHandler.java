package calculator.input.handler;

import calculator.CalculatorContext;
import calculator.input.InputContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class SimpleOperationHandler implements InputHandler {

  @Getter
  private final Character sign;

  @Getter
  private final Operation operation;

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
    calcCtx.add((CalculatorContext cctx) -> {
      return operation.apply(cctx.fetchLastItem().getValue(cctx),
          calcCtx.fetchLastItem().getValue(cctx));
    });
  }
}
