package calculator.input;

import java.util.ArrayList;
import java.util.Collection;

import calculator.CalculatorContext;
import calculator.input.handler.InputHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InputProcessor {

  private final Collection<InputHandler> handlers = new ArrayList<>();
  private final InputContext inputCtx;
  private final CalculatorContext calcCtx;

  public void addHandler(InputHandler processor) {
    handlers.add(processor);
  }

  public void process() {
    do {
      for (InputHandler handler : handlers) {
        if (handler.match(inputCtx)) {
          handler.process(inputCtx, calcCtx);
        }
      }
    } while (!inputCtx.isEmpty());
  }
}
