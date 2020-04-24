package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Main {
  public static void main(String[] args) throws IOException {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    InputContext inputCtx = new InputContext();
    CalculatorContext calcCtx = new CalculatorContextImpl();
    InputProcessor processor = new InputProcessorFactory().create(inputCtx, calcCtx);
    
    DecimalFormat format = new DecimalFormat("#.###");
    
    do {
      inputCtx.setLine(reader.readLine());
      
      if (inputCtx.isStopped()) {
        break;
      }
      
      processor.process();
      
      if (calcCtx.isEmpty()) {
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
