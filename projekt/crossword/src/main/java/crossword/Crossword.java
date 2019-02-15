package crossword;

import database.InteliCrosswordDB;
import database.Entry;

import exceptions.EntryNotFound;
import strategy.Strategy;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Klasa reprezentująca krzyżówke.
 *
 * <p>Zawiera aktualnie użyte słowa w krzyżówce, tablicę reprezentującą
 * ułożenie pojedynczych liter, bazę ze słownikiem haseł do krzyżówki, unikalne ID krzyżówki
 * oraz strategię użytą do wygenerowania jej.</p>
 *
 * @author Sandra
 */

public class Crossword implements Serializable {

    private LinkedList<CwEntry> entries;
    private Board board;
    transient private InteliCrosswordDB cwdb;
    private long ID = -1;
    private Class strategy;

    /**
     * Konstruktor przyjmujący szerokość i wysokość tablicy krzyżówki oraz bazę z hasłami.
     *
     * @param width  szerokość tablicy krzyżówki
     * @param height wysokość tablicy krzyżówki
     * @param cwdb   baza ze słownikiem haseł
     */
    public Crossword(int width, int height, InteliCrosswordDB cwdb) {
        entries = new LinkedList<>();
        board = new Board(width, height);
        this.cwdb = cwdb;
    }

    /**
     * Metoda pobierająca ID krzyżówki
     *
     * @return ID krzyżówki
     */
    public long getID() {
        return ID;
    }

    /**
     * Metoda pobierająca klasę strategii użytej do wygenerowania krzyżówki
     *
     * @return klasa strategii
     */
    public Class getStrategy() {
        return strategy;
    }

    /**
     * Metoda pobierająca listę wyrazów użytych w krzyżówce
     *
     * @return lista obiektów CwEntry
     */
    public LinkedList<CwEntry> getEntries() {
        return entries;
    }

    /**
     * Metoda pobierająca tablicę krzyżówki
     *
     * @return tablica krzyżówki
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Metoda pobierająca obiekt bazy danych
     *
     * @return baza ze słownikiem haseł
     */
    public InteliCrosswordDB getCwDB() {
        return cwdb;
    }

    /**
     * Metoda pobierająca wyraz użyty w krzyżówce
     *
     * @param index indeks wyrazu w liście
     * @return obiekt CwEntry
     */
    public CwEntry getEntry(int index) {
        return entries.get(index);
    }

    /**
     * Metoda sprawdzająca czy dany wyraz jest już użyty w krzyżówce
     *
     * @param word słowo do sprawdzenia
     * @return czy już występuje w krzyżówce
     */
    public boolean contains(String word) {
        for (Entry entry : entries) {
            if (entry.getWord().equals(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda dodająca wyraz do krzyżówki. Dodaje wyraz do słownika oraz uaktualnia tablicę.
     *
     * @param cwEntry słowo do dodania
     * @param s       strategia użyta do generowania krzyżówki
     */
    private void addCrosswordEntry(CwEntry cwEntry, Strategy s) {
        entries.add(cwEntry);
        s.updateBoard(board, cwEntry);
    }

    /**
     * Metoda generująca krzyżówkę. Domyślnie ustawia ID na bieżący czas w milisekundach.
     * Wyrzuca wyjątek tylko w przypadku prostej strategii gdy nie będzie możliwe znalezienie słowa
     * przecinającego konkretną literę hasła.
     *
     * @param s strategia użyta do generowania krzyżówki
     * @throws EntryNotFound wyjątek wyrzucany gdy nie jest możliwe znalezienie słowa do krzyżówki
     */
    public final void generate(Strategy s) throws EntryNotFound {
        ID = System.currentTimeMillis();
        strategy = s.getClass();
        CwEntry e;
        while ((e = s.findEntry(this)) != null) {
            addCrosswordEntry(e, s);
        }
    }
}