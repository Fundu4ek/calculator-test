package calculator;

public class InputProcessorFactory {
  public InputProcessor create(InputContext inputCtx, CalculatorContext calcCtx) {

    InputProcessor processor = new InputProcessor(inputCtx);

    // Handle integers
    processor.addHandler((InputContext ctx) -> {
      return ctx.getNextChar() != null && Character.isDigit(ctx.getNextChar());
    }, (InputContext ctx) -> {
      StringBuilder chars = new StringBuilder();
      while (!ctx.isEmpty() && Character.isDigit(ctx.getNextChar())) {
        chars.append(ctx.fetchNextChar());
      }
      
      calcCtx.add((CalculatorContext cctx) -> {
        return Float.parseFloat(chars.toString());
      });
    });

    // Handle mul operation
    processor.addHandler((InputContext ctx) -> {
      return ctx.getNextChar() != null && ctx.getNextChar() == '*';
    }, (InputContext ctx) -> {
      ctx.fetchNextChar(); // Remove op sign from stack
      calcCtx.add((CalculatorContext cctx) -> {
        return cctx.fetchLastItem().getValue(cctx) * cctx.fetchLastItem().getValue(cctx);
      });
    });

    // Handle plus operation
    processor.addHandler((InputContext ctx) -> {
      return ctx.getNextChar() != null && ctx.getNextChar() == '+';
    }, (InputContext ctx) -> {
      ctx.fetchNextChar(); // Remove op sign from stack
      calcCtx.add((CalculatorContext cctx) -> {
        return cctx.fetchLastItem().getValue(cctx) + cctx.fetchLastItem().getValue(cctx);
      });
    });

    // Handle subtract operation
    processor.addHandler((InputContext ctx) -> {
      return ctx.getNextChar() != null && ctx.getNextChar() == '-';
    }, (InputContext ctx) -> {
      ctx.fetchNextChar(); // Remove op sign from stack
      calcCtx.add((CalculatorContext cctx) -> {
        Float second = cctx.fetchLastItem().getValue(cctx);
        Float first = cctx.fetchLastItem().getValue(cctx);
        return first - second;
      });
    });
    
    // Handle division operation
    processor.addHandler((InputContext ctx) -> {
      return ctx.getNextChar() != null && ctx.getNextChar() == '/';
    }, (InputContext ctx) -> {
      ctx.fetchNextChar(); // Remove op sign from stack
      calcCtx.add((CalculatorContext cctx) -> {
        Float second = cctx.fetchLastItem().getValue(cctx);
        Float first = cctx.fetchLastItem().getValue(cctx);
        
        return first / second;
      });
    });
    
    return processor;
  }
}
