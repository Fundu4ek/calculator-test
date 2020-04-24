package calculator;

public interface CalculatorContext {
  public void add(CalculatorItem number);
  public CalculatorItem fetchLastItem();
  public boolean isEmpty();
}
