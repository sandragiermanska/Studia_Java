package database;

import java.io.Serializable;

/**
 * Klasa reprezentująca słowo z krzyżówki razem z pytaniem.
 *
 * @author Sandra
 */

public class Entry implements Serializable {

    private String word;
    private String clue;

    /**
     * Konstruktor przyjmujący słowo do krzyżówki i pytanie do słowa.
     *
     * @param word słowo do krzyżówki
     * @param clue pytanie do słowa
     */
    public Entry(String word, String clue) {
        this.word = word;
        this.clue = clue;
    }

    /**
     * Metoda pobierająca słowo z obiektu
     *
     * @return słowo do krzyżówki
     */
    public String getWord() {
        return word;
    }

    /**
     * Metoda pobierająca pytanie do słowa
     *
     * @return pytanie do słowa
     */
    public String getClue() {
        return clue;
    }
}