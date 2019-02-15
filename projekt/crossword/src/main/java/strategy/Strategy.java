package strategy;

import crossword.*;
import exceptions.EntryNotFound;

/**
 * Klasa abstrakcyja będąca strategią użytą do wygenerowania krzyżówki.
 * Stworzona do realizacji wzorca Strategii.
 *
 * @author Sandra
 */

public abstract class Strategy {

    /**
     * Funkcja abstrakcyjna znajdująca kolejne słowa do krzyżówki.
     *
     * @param cw krzyżówka do której dodawane są słowa
     * @return kolejne słowo wraz ze współrzędnymi
     * @throws EntryNotFound wyjątek wyrzucany gdy nie udaje sę odnaleźć słowa potrzebnego do wygenerowania krzyżówki
     */
    public abstract CwEntry findEntry(Crossword cw) throws EntryNotFound;

    /**
     * Funkcja abstrakcyjna aktualizująca tablicę krzyżówki.
     *
     * @param b tablica krzyżówki
     * @param e słowo dodawane do krzyżówki
     */
    public abstract void updateBoard(Board b, CwEntry e);

}
