package lab4;

public class EndLessThanStart extends Exception{

    String text;

    public EndLessThanStart(String temp) {
        text = "End less than start: " + temp;
    }

}
