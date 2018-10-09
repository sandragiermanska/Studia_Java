package lab2;

import java.util.LinkedList;
import java.io.*;
/**
 * Created by student on 2018-10-09.
 */
public class Test {

    public static LinkedList<Punkt3D> punkty = new LinkedList<Punkt3D>();

    public static void main(String[] args) {
        int opcja = 0;
        InputStreamReader rd = new InputStreamReader(System.in);
        BufferedReader bfr = new BufferedReader(rd);

        while (opcja != 4) {
            System.out.println("1 Wczytaj punkt 3D");
            System.out.println("2 Wyswietl wszystkie punkty");
            System.out.println("3 Oblicz odleglosc");
            System.out.println("4 Zakoncz");

            try {
                opcja = Integer.parseInt(bfr.readLine());

                switch (opcja) {
                    case 1: {
                        double x, y, z;
                        System.out.println("Podaj wspolrzedna x");
                        x = Double.parseDouble(bfr.readLine());
                        System.out.println("Podaj wspolrzedna y");
                        y = Double.parseDouble(bfr.readLine());
                        System.out.println("Podaj wspolrzedna z");
                        z = Double.parseDouble(bfr.readLine());

                        Punkt3D nowyPunkt = new Punkt3D(x, y, z);
                        punkty.add(nowyPunkt);
                        break;
                    }
                    case 2: {
                        int size = punkty.size();
                        for (int i = 0; i < size; i++) {
                            punkty.get(i).print();
                        }
                        break;
                    }
                    case 3: {
                        int size = punkty.size();
                        for (int i = 0; i < size; i++) {
                            for (int j = i+1; j < size; j++) {
                                System.out.println("Odleglosc miedzy punktem "+i+" a "+j+": "+punkty.get(i).distance(punkty.get(j)));
                            }
                        }
                        break;
                    }
                    case 4:
                    default:
                }

            } catch (IOException ex) {}

        }

    }
}