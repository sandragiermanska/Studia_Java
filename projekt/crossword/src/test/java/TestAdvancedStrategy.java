import org.junit.Test;

import static org.junit.Assert.fail;
import strategy.Strategy;
import strategy.AdvancedStrategy;
import crossword.Crossword;
import database.InteliCrosswordDB;

public class TestAdvancedStrategy {

    @Test
    public void test50ExecuteWithoutExceptions() {
        InteliCrosswordDB db = new InteliCrosswordDB("C:\\Users\\Sandra\\Desktop\\Studia\\III semestr\\PO_Java\\Repo\\projekt\\crossword\\src\\main\\resources\\cwdb.txt");
        Strategy strategy = new AdvancedStrategy();
        for (int i = 0; i < 100; i++) {
            Crossword crossword = new Crossword( 31, 17, db);
            try {
                crossword.generate(strategy);
                System.out.println(i);
            } catch (Exception e) {
                e.printStackTrace();
                fail("Wyrzucono wyjątek, iteracja:" + i);
            }
        }
    }

}
