package lab4;

public class WrongCharacter extends Exception {

    String text;

    public WrongCharacter(String temp) {
        text = "Wrong character: " + temp;
    }

}
