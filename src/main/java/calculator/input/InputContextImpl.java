package calculator.input;

import lombok.Getter;
import lombok.Setter;

public class InputContextImpl implements InputContext {
  private final static String EMPTY = "";

  @Getter
  @Setter
  private boolean stopped = false;
  private String currentLine = EMPTY;
  private int currentPos = 0;

  public void setLine(String line) {
    currentPos = 0;

    if (line == null) {
      currentLine = String.valueOf(COMMAND_QUIT_CHAR);
    } else {
      currentLine = line;
    }
  }

  public boolean isEmpty() {
    return stopped || currentPos >= currentLine.length();
  }

  public Character getNextChar() {
    return isEmpty() ? null : currentLine.charAt(currentPos);
  }

  public Character getAfterNextChar() {
    return (stopped || currentPos + 1 >= currentLine.length())
        ? null
        : currentLine.charAt(currentPos + 1);
  }

  public Character fetchNextChar() {
    return isEmpty() ? null : currentLine.charAt(currentPos++);
  }
}
