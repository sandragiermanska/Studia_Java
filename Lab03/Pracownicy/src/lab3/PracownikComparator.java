package lab3;

import java.util.Comparator;

public class PracownikComparator implements Comparator<Pracownik> {
    @Override
    public int compare(Pracownik o1, Pracownik o2) {
        int result;
        if (o1.wynagrodzenieBrutto < o2.wynagrodzenieBrutto) {
            result = -1;
        } else if (o1.wynagrodzenieBrutto == o2.wynagrodzenieBrutto) {
            result = 0;
        } else {
            result = 1;
        }
        return result;
    }
}
