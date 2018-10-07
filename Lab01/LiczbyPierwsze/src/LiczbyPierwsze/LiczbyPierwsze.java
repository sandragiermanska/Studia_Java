package LiczbyPierwsze;

public class LiczbyPierwsze {
    private int maximum;

    public LiczbyPierwsze(int _maximum) {
        maximum = _maximum;
    }

    public void WypiszLiczbyPierwsze() {
        for (int i = 2; i < maximum; i++) {
            boolean pierwsza = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0 && j != i) {
                    pierwsza = false;
                    break;
                }
            }
            if (pierwsza) {
                System.out.println(i);
            }
        }
    }
}
