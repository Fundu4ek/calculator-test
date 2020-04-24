package calculator;

public class InputProcessorBuilder {
  public InputProcessor build(InputContext inputCtx, CalculatorContext calcCtx) {

    InputProcessor processor = new InputProcessor(inputCtx);

    // Handle integers
    processor.addHandler((InputContext ctx) -> {
      final Character ch = ctx.getNextChar();

      if (ch == null) {
        return false;
      }

      if (Character.isDigit(ch)) {
        return true;
      }

      if (ch == '-') {
        final Character afterNext = ctx.getAfterNextChar();
        if (afterNext == null) {
          return false;
        }

        if (Character.isDigit(afterNext)) {
          return true;
        }
      }

      return false;
    }, (InputContext ctx) -> {
      StringBuilder chars = new StringBuilder();

      // Handle negative values
      if (ctx.getNextChar() == '-') {
        chars.append(ctx.fetchNextChar());
      }

      while (!ctx.isEmpty() && ('.' == ctx.getNextChar() || Character.isDigit(ctx.getNextChar()))) {
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
      final Character ch = ctx.getNextChar();
      
      if (ch == null || ch != '-') {
        return false;
      }
      
      final Character afterNext = ctx.getAfterNextChar();
      
      if (afterNext != null && Character.isDigit(afterNext)) {
        // Got negative number (not an operation)
        return false;
      }
      
      return true;
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
