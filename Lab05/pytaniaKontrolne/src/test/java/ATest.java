import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.*;

/**
 * Created by student on 2018-10-30.
 */
public class ATest {

    @Test
    public void testMet1() throws Exception {
        Assert.assertEquals("pierwszy", A.met(1));
    }

    @Test
    public void testMet2() throws Exception {
        Assert.assertEquals("drugi", A.met(2));
    }

    @Test
    public void testMet3() throws Exception {
        Assert.assertEquals("inny", A.met(3));
        Assert.assertEquals("inny", A.met(-3));
    }
}