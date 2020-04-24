package calculator;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString
@Slf4j
public class InputContext {
  private final static String EMPTY = "";
  
  @Getter
  boolean stopped = false;
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

  public Character fetchNextChar() {
    return isEmpty() ? null : data.charAt(currentPos++);
  }
}
