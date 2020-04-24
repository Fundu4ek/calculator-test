package calculator;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorContextImplTest {

  private CalculatorContext calcCtx;

  @BeforeEach
  public void createInstance() {
    calcCtx = new CalculatorContextImpl();
  }
  
  @Test
  public void testEmptyValue() {
    Assert.assertEquals(0f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }

  @Test
  public void testSimpleValue() {
    calcCtx.add((CalculatorContext cctx) -> { return 10f; });
    Assert.assertEquals(10f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }

  @Test
  public void testTwoSimpleValues() {
    calcCtx.add((CalculatorContext cctx) -> { return 10f; });
    calcCtx.add((CalculatorContext cctx) -> { return 20f; });
    Assert.assertEquals(20f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
    Assert.assertEquals(10f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
  
  @Test
  public void testSimpleValueAndEmpty() {
    calcCtx.add((CalculatorContext cctx) -> { return 10f; });
    Assert.assertEquals(10f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
    Assert.assertEquals(0f, calcCtx.fetchLastItem().getValue(calcCtx), CommonConstants.DELTA);
  }
}