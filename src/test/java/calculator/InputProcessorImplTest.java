package calculator;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputProcessorImplTest {

  private InputProcessor processor;
  
  private InputContext inputCtx;
  
  private CalculatorContext calcCtx;

  @BeforeEach
  public void createInstance() {
    inputCtx = new InputContextImpl();
    calcCtx = new CalculatorContextImpl();
    this.processor = new InputProcessorBuilder().build(inputCtx, calcCtx);
  }
  
  
  @Test
  public void testSum() {
    inputCtx.setLine("81 19 +");
    processor.process();
    Assert.assertEquals(100f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }

  @Test
  public void testSub() {
    inputCtx.setLine("20 19 -");
    processor.process();
    Assert.assertEquals(1.0f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testMul() {
    inputCtx.setLine("20 11 *");
    processor.process();
    Assert.assertEquals(220.0f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testDiv() {
    inputCtx.setLine("20 8 /");
    processor.process();
    Assert.assertEquals(2.5f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }

  @Test
  public void testMultiline() {
    inputCtx.setLine("20");
    processor.process();
    inputCtx.setLine("8");
    processor.process();
    inputCtx.setLine("-");
    processor.process();
    Assert.assertEquals(12f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testPreviousResult() {
    inputCtx.setLine("8 2 +"); //10
    processor.process();
    inputCtx.setLine("20 +"); //10 + 20
    processor.process();
    Assert.assertEquals(30f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testOneParam() {
    inputCtx.setLine("2 +");
    processor.process();
    Assert.assertEquals(2f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testSingleOperator() {
    inputCtx.setLine("+");
    processor.process();
    Assert.assertEquals(0f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testZeroDivision() {
    inputCtx.setLine("2 0 /");
    processor.process();
    Assert.assertEquals(Float.POSITIVE_INFINITY, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
}
