package calculator;

import java.util.Stack;

public class CalculatorContextImpl implements CalculatorContext {
  private Stack<CalculatorItem> data = new Stack<>();

  @Override
  public void add(CalculatorItem number) {
    data.add(number);
  }

  @Override
  public CalculatorItem fetchLastItem() {
    return data.pop();
  }

  @Override
  public boolean isEmpty() {
    return !data.isEmpty();
  }
}
