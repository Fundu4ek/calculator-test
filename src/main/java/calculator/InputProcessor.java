package calculator;

import java.util.HashMap;
import java.util.Map;

public class InputProcessor {
  
  private final Map<ProcessingMatcher, Processor> config = new HashMap<>();
  private final InputContext ctx;
  
  public InputProcessor(InputContext ctx) {
    this.ctx = ctx;
  }

  public void addHandler(ProcessingMatcher matcher, Processor processor) {
    config.put(matcher, processor);
  }
  
  public void process() {
    do {
      boolean matched = false;
      
      for(Map.Entry<ProcessingMatcher, Processor> m: config.entrySet()) {
        if (m.getKey().match(ctx)) {
          m.getValue().process(ctx);
          matched = true;
        }
      }
      
      if (!matched) {
        ctx.fetchNextChar(); // Skip unknown char
      }
      
    } while(!ctx.isEmpty());
  }
}
