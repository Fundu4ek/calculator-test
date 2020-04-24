package calculator;

public interface InputContext {
  public boolean isEmpty();
  public boolean isStopped();

  public void setLine(String line);
  
  public Character getNextChar();
  public Character fetchNextChar();
}
