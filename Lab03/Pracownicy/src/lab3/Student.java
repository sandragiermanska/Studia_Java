package lab3;

public class Student extends Pracownik {

    @Override
    public double wynagrodzenieNetto() {
        return wynagrodzenieBrutto;
    }

}
