package view;

import crossword.Point;

/**
 * Klasa będąca elementem listy punktów (punkty z jednego hasła).
 *
 * @author Sandra
 */

public class CellView extends Point {

    private CellView next;

    /**
     * Konstruktor przyjmujący współrzędne x i y
     *
     * @param x współrzędna x
     * @param y współrzędna y
     */
    public CellView(int x, int y) {
        super(x, y);
        next = null;
    }

    /**
     * Funkcja ustwiająca wskażnik na następną komórkę
     *
     * @param next następna komórka
     */
    public void setNext(CellView next) {
        this.next = next;
    }

    /**
     * Funkcja pobierająca wskażnik następnej komórki
     *
     * @return następna komórka
     */
    public CellView getNext() {
        return next;
    }
}
