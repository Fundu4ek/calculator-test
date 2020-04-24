package calculator;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputContextImplTest {

  private InputContext inputCtx;

  @BeforeEach
  public void createInstance() {
    inputCtx = new InputContextImpl();
  }
  
  @Test
  public void testDefaultStop() {
    Assert.assertEquals(false, inputCtx.isStopped());
  }

  @Test
  public void testStopBehavior() {
    inputCtx.setLine(null);
    Assert.assertEquals(true, inputCtx.isStopped());
  }
  
  @Test
  public void testFetching() {
    inputCtx.setLine("123");
    Assert.assertTrue('1' == inputCtx.fetchNextChar());
    Assert.assertTrue('2' == inputCtx.fetchNextChar());
    Assert.assertTrue('3' == inputCtx.fetchNextChar());
  }
  
  @Test
  public void testFetchingWithAdditionalRead() {
    inputCtx.setLine("12");
    Assert.assertTrue('1' == inputCtx.fetchNextChar());
    Assert.assertTrue('2' == inputCtx.fetchNextChar());
    Assert.assertTrue(null == inputCtx.fetchNextChar());
  }
  
  @Test
  public void testGetWithFetch() {
    inputCtx.setLine("12");
    Assert.assertTrue('1' == inputCtx.fetchNextChar());
    Assert.assertTrue('2' == inputCtx.getNextChar());
    Assert.assertTrue('2' == inputCtx.fetchNextChar());
  }
}