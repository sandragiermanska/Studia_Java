import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.io.*;
//import static org.junit.Assert.*;

/**
 * Created by student on 2018-10-30.
 */
public class MammothTest {

    @BeforeClass
    public static void createFile() throws FileNotFoundException {
        PrintWriter cos = new PrintWriter("cos.txt");
        cos.close();
    }

    @Test(expected = InadequateFoodException.class)
    public void testEat() throws Exception {
        m.eat(meat);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    Mammoth m = new Mammoth();
    Meat meat = new Meat();

    @Test public void throwsInadequateFoodException() throws InadequateFoodException {
        thrown.expect(InadequateFoodException.class);
        thrown.expectMessage("I don't like meat");

        m.eat(meat);
    }

    @AfterClass
    public static void deleteFile() throws FileNotFoundException {
        File file = new File("cos.txt");
        file.delete();
    }
}