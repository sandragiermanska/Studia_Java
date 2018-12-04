package lab7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Kadra kadra = new Kadra();
        boolean end = false;
        int option = 0;
        while (!end) {
            try {
                System.out.println("1 - Wyszukaj pracownika");
                System.out.println("2 - Dodaj pracownika");
                System.out.println("3 - Usuń pracownika");
                System.out.println("4 - Pobierz wynagrodzenie brutto pracownika");
                System.out.println("5 - Pobierz wynagrodzenie netto pracownika");
                System.out.println("6 - Wyjdź");

                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                option = Integer.parseInt(in.readLine());

                String pesel;
                ArrayList<String> result;
                double wynagrodzenie;
                int czyStudent;
                switch (option) {
                    case 1:
                        System.out.println("Podaj pesel");
                        pesel = in.readLine();
                        result = kadra.select(pesel);
                        System.out.println(result);
                        break;
                    case 2:
                        System.out.println("Podaj pesel");
                        pesel = in.readLine();
                        System.out.println("Podaj wynagodzenie brutto");
                        wynagrodzenie = Double.parseDouble(in.readLine());
                        System.out.println("0 - Pracownik etatowy\n1 - Student");
                        czyStudent = Integer.parseInt(in.readLine());
                        kadra.add(pesel, wynagrodzenie, czyStudent);
                        break;
                    case 3:
                        System.out.println("Podaj pesel");
                        pesel = in.readLine();
                        kadra.delete(pesel);
                        break;
                    case 4:
                        System.out.println("Podaj pesel");
                        pesel = in.readLine();
                        wynagrodzenie = kadra.getWynagrodzenieBrutto(pesel);
                        System.out.println(wynagrodzenie);
                        break;
                    case 5:
                        System.out.println("Podaj pesel");
                        pesel = in.readLine();
                        wynagrodzenie = kadra.getWynagrodzenieNetto(pesel);
                        System.out.println(wynagrodzenie);
                        break;
                    case 6:
                        end = true;
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
            }
        }
    }
}
