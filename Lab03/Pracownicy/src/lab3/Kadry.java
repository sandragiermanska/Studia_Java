package lab3;

import java.util.Collections;
import java.util.LinkedList;

public class Kadry {

    LinkedList<Pracownik> kadra = new LinkedList<>();

    public void add(Pracownik pracownik) {
        kadra.add(pracownik);
    }

    public Pracownik find(String pesel) {
        int size = kadra.size();
        Pracownik result = null;
        for (int i = 0; i < size; i++) {
            if(pesel.equals(kadra.get(i).pesel)) {
                result = kadra.get(i);
            }
        }
        return result;
    }

    public void delete(Pracownik pracownik) {
        kadra.remove(pracownik);
    }

    public double getWynagrodzenieBrutto(String pesel) {
        Pracownik pracownik = find(pesel);
        if (pracownik != null) {
            return pracownik.wynagrodzenieBrutto;
        }
        return -1;
    }

    public double getWynagrodzenieNetto(String pesel) {
        Pracownik pracownik = find(pesel);
        if (pracownik != null) {
            return pracownik.wynagrodzenieNetto();
        }
        return -1;
    }

    public void sort() {
        Collections.sort(kadra, new PracownikComparator());
    }

}
