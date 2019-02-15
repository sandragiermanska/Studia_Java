package crossword;

import java.io.Serializable;

/**
 * Klasa reprezentująca punkt dwuwymiarowy.
 * Współrzędne punktu: x - po szerokości, y - po wysokości
 *
 * @author Sandra
 */

public class Point implements Serializable {

    private int x;
    private int y;

    /**
     * Konstruktor przyjmujący współrzędne punktu.
     *
     * @param x współrzędna po szerokości
     * @param y współrzędna po wysokości
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Funkcja ustawiająca współrzędną y
     *
     * @param y współrzędna po wysokości
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Funkcja ustawiająca współrzędną x
     *
     * @param x współrzędna po szerokości
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Funkcja zwracająca współrzędną y
     *
     * @return współrzędna po wysokości
     */
    public int getY() {
        return y;
    }

    /**
     * Funkcja zwracająca współrzędną x
     *
     * @return współrzędna po szerokości
     */
    public int getX() {
        return x;
    }
}