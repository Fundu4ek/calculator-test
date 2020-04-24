package calculator.input.handler;

import calculator.input.InputContext;

public class MinusOperationHandler extends SimpleOperationHandler {

  public MinusOperationHandler(Character sign, Operation operation) {
    super(sign, operation);
  }

  @Override
  public boolean match(InputContext inputCtx) {
    
    final Character ch = inputCtx.getNextChar();

    if (ch == null || ch != getSign()) {
      return false;
    }
    
    final Character afterNext = inputCtx.getAfterNextChar();
    
    if (afterNext != null && Character.isDigit(afterNext)) {
      // Got negative number (not an operation)
      return false;
    }
    
    // Remove op sign from stack
    inputCtx.fetchNextChar(); 
    return true;
  }
}
