package calculator;

public interface CalculatorContext {
  void add(CalculatorItem number);
  CalculatorItem fetchLastItem();
  CalculatorItem getLastItem();
  boolean isEmpty();
}
