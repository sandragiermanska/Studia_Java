/**
 * Created by student on 2018-10-30.
 */
public class Mammoth {
    public void eat(Food food) {
        if( food instanceof Meat) {
            throw new InadequateFoodException("I don't like meat");
        }
    }
}