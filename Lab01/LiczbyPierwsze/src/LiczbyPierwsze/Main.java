package LiczbyPierwsze;

import javaIn.JIn;

public class Main {

    public static void main(String[] args) {
        System.out.println("Podaj liczbe:");
        int liczba = JIn.getInt();
        LiczbyPierwsze max = new LiczbyPierwsze(liczba);
        max.WypiszLiczbyPierwsze();
    }
}
