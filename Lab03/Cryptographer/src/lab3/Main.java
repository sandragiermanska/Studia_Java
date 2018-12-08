package lab3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Podano za mało argumentów");
            return;
        }
        File input = new File(args[0]);
        String output = args[1];

        int option1 = 0;
        int option2 = 0;
        while (option1 != 1 && option1 != 2 && option2 != 1 && option2 != 2) {
            try {
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Co chcesz zrobić?");
                System.out.println("1 - Szyfrować");
                System.out.println("2 - Deszyfrować");
                String opt = bf.readLine();
                option1 = Integer.parseInt(opt);
                System.out.println("Jaki algorytm szyfrujący wybierasz?");
                System.out.println("1 - ROT11");
                System.out.println("2 - Polibiusz");
                opt = bf.readLine();
                option2 = Integer.parseInt(opt);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (option1 == 1 && option2 == 1) {
            Cryptographer.cryptfile(input, output, new ROT11());
        } else if (option1 == 1 && option2 == 2) {
            Cryptographer.cryptfile(input, output, new Polibiusz());
        } else if (option1 == 2 && option2 == 1) {
            Cryptographer.decryptfile(input, output, new ROT11());
        } else {
            Cryptographer.decryptfile(input, output, new Polibiusz());
        }
    }
}
