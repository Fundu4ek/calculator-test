package calculator.input;

public interface InputContext {

  public final static Character COMMAND_QUIT_CHAR = 'q';

  boolean isEmpty();

  boolean isStopped();
  
  void setStopped(boolean stopped);

  /**
   * Set current String line for input context.
   * Pass null to switch input context state to stopped.
   */
  void setLine(String line);

  /**
   * Retrieves next character from line
   */
  Character getNextChar();

  /**
   * Retrieves next character after next from line
   */
  Character getAfterNextChar();
  
  /**
   * Retrieves next character from line and moves internal pointer to next character of available.
   */
  Character fetchNextChar();
}
