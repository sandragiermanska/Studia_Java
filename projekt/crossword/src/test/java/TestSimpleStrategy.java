import crossword.Crossword;
import database.InteliCrosswordDB;
import org.junit.Test;
import strategy.SimpleStrategy;
import strategy.Strategy;

public class TestSimpleStrategy {

    @Test
    public void test50ExecuteWithoutExceptions() {
        InteliCrosswordDB db = new InteliCrosswordDB("C:\\Users\\Sandra\\Desktop\\Studia\\III semestr\\PO_Java\\Repo\\projekt\\crossword\\src\\main\\resources\\cwdb.txt");
        Strategy strategy = new SimpleStrategy();
        for (int i = 0; i < 100; i++) {
            Crossword crossword = new Crossword( 31, 17, db);
            try {
                crossword.generate(strategy);
                System.out.println(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
