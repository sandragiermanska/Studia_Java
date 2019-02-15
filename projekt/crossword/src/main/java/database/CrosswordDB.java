package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Baza danych haseł do krzyżówek.
 *
 * <p>Bazowa klasa bazy danych odpowiedzialna za odczyt z pliku haseł
 * do krzyżówki, i przechowywanie ich w postaci listy.</p>
 *
 * @author Sandra
 */

public class CrosswordDB {

    protected LinkedList<Entry> dictionary = new LinkedList<>();

    /**
     * Konstruktor klasy tworzący bazę danych przy pomocy funkcji createCB.
     *
     * @param filename ścieżka do pliku z hasłami do krzyżówki
     */
    public CrosswordDB(String filename) {
        createDB(filename);
    }

    /**
     * Funkcja dodająca jedno hasło do słownika bazy danych.
     *
     * @param word słowo do krzyżówki
     * @param clue pytanie do słowa
     */
    public void add(String word, String clue) {
        Entry entry = new Entry(word, clue);
        dictionary.add(entry);
    }

    /**
     * Funkcja do pobrania obiektu Entry na ze słownika.
     * Jeżeli nie ma elementu pasującego do słowa podanego w argumencie
     * to zwraca null.
     *
     * @param word słowo do krzyżówki
     * @return obiekt Entry z bazy lub null
     */
    public Entry get(String word) {
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).getWord().equals(word)) {
                return dictionary.get(i);
            }
        }
        return null;
    }

    /**
     * Funkcja do usunięcia danego hasła z bazy na podstawie słowa.
     * Jeżeli nie istnieje taki obiekt w słowniku to nic nie usuwa.
     *
     * @param word słowo do krzyżówki
     */
    public void remove(String word) {
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).getWord().equals(word)) {
                dictionary.remove(i);
                break;
            }
        }
    }

    /**
     * Funkcja zwracająca wielkość słownika.
     *
     * @return rozmiar słownika
     */
    public int getSize() {
        return dictionary.size();
    }

    /**
     * Funkcja tworząca bazę z pliku.
     *
     * @param filename ścieżka do pliku
     */
    protected void createDB(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            //int i = 0;
            String word, clue, text;
            Pattern pattern = Pattern.compile("\\[(.*)\\] (.*)");
            Matcher matcher;
            while (scanner.hasNextLine()) {
                word = scanner.nextLine();
                text = scanner.nextLine();
                matcher = pattern.matcher(text);
                if (matcher.matches()) {
                    clue = matcher.group(2);
                } else {
                    clue = text;
                }
                add(word, clue);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}