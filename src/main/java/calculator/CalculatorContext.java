package calculator;

public interface CalculatorContext {
  void add(CalculatorItem number);
  CalculatorItem fetchLastItem();
  boolean isEmpty();
}
