package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Cryptographer {

    public static void cryptfile(File fileIn, String fileOut, Algorithm algorithm) {
        try {
            Scanner read = new Scanner(fileIn);
            PrintWriter write = new PrintWriter(fileOut);
            String textIn, textOut;
            while (read.hasNextLine()) {
                textIn = read.nextLine();
                textOut = algorithm.crypt(textIn);
                write.println(textOut);
            }
            write.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void decryptfile(File fileIn, String fileOut, Algorithm algorithm) {
        try {
            Scanner read = new Scanner(fileIn);
            PrintWriter write = new PrintWriter(fileOut);
            String textIn, textOut;
            while (read.hasNextLine()) {
                textIn = read.nextLine();
                textOut = algorithm.decrypt(textIn);
                write.println(textOut);
            }
            write.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

interface Algorithm {

    String crypt(String text);
    String decrypt(String text);

}