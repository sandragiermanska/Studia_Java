package lab9;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class GenerateRandoms {

    public static void generate() {
        try {
            PrintWriter zapis = new PrintWriter("randoms.txt");
            Random generator = new Random();
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 10000; j++) {
                    zapis.print(generator.nextInt(10));
                }
                zapis.println();
            }
            zapis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }
}
