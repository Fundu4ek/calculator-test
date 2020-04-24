package calculator;

import java.util.Stack;

public class CalculatorContextImpl implements CalculatorContext {
  private final static CalculatorItem EMPTY_ITEM = (CalculatorContext cctx) -> {
    return 0f;
  };

  private Stack<CalculatorItem> data = new Stack<>();

  @Override
  public void add(CalculatorItem number) {
    data.add(number);
  }

  @Override
  public CalculatorItem fetchLastItem() {
    return data.isEmpty() ? EMPTY_ITEM : data.pop();
  }

  @Override
  public boolean isEmpty() {
    return data.isEmpty();
  }
}
