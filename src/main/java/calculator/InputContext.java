package calculator;

public interface InputContext {
  boolean isEmpty();
  boolean isStopped();

  void setLine(String line);
  
  Character getNextChar();
  Character fetchNextChar();
}
