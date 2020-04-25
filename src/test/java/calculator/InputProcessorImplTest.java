package calculator;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.input.InputContext;
import calculator.input.InputContextImpl;
import calculator.input.InputProcessor;

public class InputProcessorImplTest {

  private InputProcessor processor;
  
  private InputContext inputCtx;
  
  private CalculatorContext calcCtx;

  @BeforeEach
  public void createInstance() {
    inputCtx = new InputContextImpl();
    calcCtx = new CalculatorContextImpl();
    processor = new InputProcessorBuilder().build();
  }
  
  
  @Test
  public void testSum() {
    inputCtx.setLine("81 19 +");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(100f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }

  @Test
  public void testSub() {
    inputCtx.setLine("20 19 -");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(1.0f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testMul() {
    inputCtx.setLine("20 11 *");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(220.0f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testDiv() {
    inputCtx.setLine("20 8 /");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(2.5f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }

  @Test
  public void testMultiline() {
    inputCtx.setLine("20");
    processor.process(inputCtx, calcCtx);
    inputCtx.setLine("8");
    processor.process(inputCtx, calcCtx);
    inputCtx.setLine("-");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(12f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testPreviousResult() {
    inputCtx.setLine("8 2 +"); //10
    processor.process(inputCtx, calcCtx);
    inputCtx.setLine("20 +"); //10 + 20
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(30f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testOneParam() {
    inputCtx.setLine("2 +");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(2f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testSingleOperator() {
    inputCtx.setLine("+");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(0f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testZeroDivision() {
    inputCtx.setLine("2 0 /");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(Float.POSITIVE_INFINITY, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testNegativeNumber() {
    inputCtx.setLine("-10");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(-10f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testNegativeNumbersMul() {
    inputCtx.setLine("-2 -10 *");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(20f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testNegativeNumbersSub() {
    inputCtx.setLine("-2 -10 -");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(8f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testDecimalNumber() {
    inputCtx.setLine("2.5");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(2.5f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testDecimalNumbersMul() {
    inputCtx.setLine("2.5 2 *");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(5f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testNegativeDecimalNumber() {
    inputCtx.setLine("-12.55");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(-12.55f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testNegativeDecimalNumbersSum() {
    inputCtx.setLine("-12.55 4.55 +");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(-8f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testMultipleOperations() {
    inputCtx.setLine("1 2 +");
    processor.process(inputCtx, calcCtx);
    inputCtx.setLine("3 *");
    processor.process(inputCtx, calcCtx);
    inputCtx.setLine("10 -");
    processor.process(inputCtx, calcCtx);
    Assert.assertEquals(-1f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testQuitOperation() {
    Assert.assertFalse(inputCtx.isStopped());
    inputCtx.setLine(null);
    processor.process(inputCtx, calcCtx);
    Assert.assertTrue(inputCtx.isStopped());
  }
  
  @Test
  public void testQuitOperation2() {
    Assert.assertFalse(inputCtx.isStopped());
    inputCtx.setLine(String.valueOf(InputContext.COMMAND_QUIT_CHAR));
    processor.process(inputCtx, calcCtx);
    Assert.assertTrue(inputCtx.isStopped());
  }
}
