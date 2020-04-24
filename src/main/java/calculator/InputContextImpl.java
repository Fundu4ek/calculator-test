package calculator;

import lombok.Getter;

public class InputContextImpl implements InputContext {
  private final static String EMPTY = "";

  @Getter
  private boolean stopped = false;
  private String data = EMPTY;
  private int currentPos = 0;

  public void setLine(String line) {
    currentPos = 0;

    if (line == null) {
      data = EMPTY;
      stopped = true;
    } else {
      data = line;
    }
  }

  public boolean isEmpty() {
    return stopped || currentPos >= data.length();
  }

  public Character getNextChar() {
    return isEmpty() ? null : data.charAt(currentPos);
  }

  public Character getAfterNextChar() {
    return (stopped || currentPos + 1 >= data.length()) ? null : data.charAt(currentPos + 1);
  }

  public Character fetchNextChar() {
    return isEmpty() ? null : data.charAt(currentPos++);
  }
}
