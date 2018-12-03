package lab3;

public class PracownikEtatowy extends Pracownik {



    @Override
    public double wynagrodzenieNetto() {
        return 0.8 * wynagrodzenieBrutto;
    }

}
