package lab9;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) {
        GenerateRandoms.generate();
        try {
            PrintWriter writer = new PrintWriter("results.txt");
            Scanner scanner = new Scanner(new File("randoms.txt"));
            ArrayList<FutureTask<ArrayList<Integer>>> tasks = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                String line = scanner.nextLine();
                tasks.add(new FutureTask<>(new MaxMultiThread(
                        line,
                        new Function() {
                            @Override
                            public int function(int number) {
                                return number / 2;    //funkcja użyta na liczbach
                            }
                        })));
                new Thread(tasks.get(i)).start();
            }

            for (int i = 0; i < 100; i++) {
                writer.print(tasks.get(i).get());
                writer.println();
            }

            writer.close();

        } catch (FileNotFoundException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
