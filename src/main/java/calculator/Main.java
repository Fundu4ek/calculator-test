package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import calculator.input.InputContext;
import calculator.input.InputContextImpl;
import calculator.input.InputProcessor;

public class Main {
  public static void main(String[] args) throws IOException {

    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final InputContext inputCtx = new InputContextImpl();
    final CalculatorContext calcCtx = new CalculatorContextImpl();
    final InputProcessor processor = new InputProcessorBuilder().build();
    final DecimalFormat format = new DecimalFormat("#.####");
    
    do {
      inputCtx.setLine(reader.readLine());
      
      processor.process(inputCtx, calcCtx);
      
      if (!inputCtx.isStopped()) {
        final Float result = calcCtx.fetchLastItem().getValue(calcCtx);
        calcCtx.add((CalculatorContext ctx) -> {
          return result;
        });

        System.out.println(format.format(result));
      }
    } while(!inputCtx.isStopped());
    
    reader.close();
  }
}
