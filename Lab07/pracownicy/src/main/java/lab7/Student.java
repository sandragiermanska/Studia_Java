package lab7;

public class Student extends Pracownik {

    @Override
    public double wynagrodzenieNetto() {
        return wynagrodzenieBrutto;
    }

}
