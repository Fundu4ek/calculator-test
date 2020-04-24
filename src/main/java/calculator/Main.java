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

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    InputContext inputCtx = new InputContextImpl();
    CalculatorContext calcCtx = new CalculatorContextImpl();
    InputProcessor processor = new InputProcessorBuilder().build(inputCtx, calcCtx);
    
    DecimalFormat format = new DecimalFormat("#.####");
    
    do {
      inputCtx.setLine(reader.readLine());
      
      if (inputCtx.isStopped()) {
        break;
      }
      
      processor.process();
      
      if (calcCtx.isEmpty()) {
        // No numbers or operations were added
        continue;
      }
      
      Float result = calcCtx.fetchLastItem().getValue(calcCtx);
      calcCtx.add((CalculatorContext ctx) -> {
        return result;
      });

      System.out.println(format.format(result));
    } while(true);
    
    reader.close();
  }
}
