package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("testOrg.txt");
            Scanner in = new Scanner(file);
            PrintWriter saving = new PrintWriter("text.txt");
            while (in.hasNext()) {
                String text = in.nextLine();
                String result = new String();
                try {
                    result = MicroDVD.delay(text,2500,25);
                } catch (WrongCharacter e) {
                    System.out.println(e.text);
                } catch (EndLessThanStart e) {
                    System.out.println(e.text);
                }
                saving.print(result);
                saving.print("\n");
            }
            saving.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
