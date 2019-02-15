package crossword;

import database.Entry;

/**
 * Klasa dziedzicząca po Entry reprezentująca słowo z krzyżówki
 * razem z pytaniem w krzyżówce. Posiada dodatkowo punkt startowy słowa,
 * jego kierunek (pionowy, poziomy) oraz informację czy ma być widoczny w widoku
 * krzyżówki(czy ma być widoczny numerek oraz pytanie do słowa) - niewidoczny tylko
 * w wypadku hasła w krzyżówce poziomej.
 *
 * @author Sandra
 */

public class CwEntry extends Entry {

    private Point firstPoint;
    private Direction direction;
    private boolean visible;

    /**
     * Konstruktor przyjmujący słowo do krzyżówki i pytanie do słowa.
     * Ustawia domyślnie punkt startowy na (0,0) oraz że ma być widoczny w widoku krzyżówki.
     *
     * @param word słowo do krzyżówki
     * @param clue pytanie do słowa
     */
    public CwEntry(String word, String clue) {
        super(word, clue);
        firstPoint = new Point(0, 0);
        visible = true;
    }

    /**
     * Konstruktor przyjmujący obiekt Entry, współrzędne punktu startowego oraz kierunek.
     * Ustawia domyślnie, że ma być widoczny w widoku krzyżówki.
     *
     * @param entry obiekt Entry - słowo do krzyżówki z pytaniem
     * @param x     współrzędna punktu startowego x
     * @param y     współrzędna punktu startowego y
     * @param d     kierunek słowa
     */
    public CwEntry(Entry entry, int x, int y, Direction d) {
        super(entry.getWord(), entry.getClue());
        firstPoint = new Point(x, y);
        direction = d;
        visible = true;
    }

    /**
     * Funkcja ustawiająca czy obiekt ma być widoczny w widoku krzyżówki.
     *
     * @param visible czy ma być widoczne w widoku krzyżówki
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Funkcja pobierająca czy obiekt ma być widoczny w widoku krzyżówki.
     *
     * @return czy ma być widoczne w widoku krzyżówki
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Funkcja ustawiająca współrzędną x punktu startowego.
     *
     * @param firstPosX współrzędna x punktu startowego
     */
    public void setFirstPosX(int firstPosX) {
        this.firstPoint.setX(firstPosX);
    }

    /**
     * Funkcja ustawiająca współrzędną y punktu startowego.
     *
     * @param firstPosY współrzędna y punktu startowego
     */
    public void setFirstPosY(int firstPosY) {
        this.firstPoint.setY(firstPosY);
    }

    /**
     * Funkcja ustawiająca kierunek.
     *
     * @param direction kierunek
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Funkcja pobierająca współrzędną x punktu startowego.
     *
     * @return współrzędna x punktu startowego
     */
    public int getFirstPosX() {
        return firstPoint.getX();
    }

    /**
     * Funkcja pobierająca współrzędną y punktu startowego.
     *
     * @return współrzędna y punktu startowego
     */
    public int getFirstPosY() {
        return firstPoint.getY();
    }

    /**
     * Funkcja pobierająca punkt startowy.
     *
     * @return punkt startowy
     */
    public Point getFirstPoint() {
        return firstPoint;
    }

    /**
     * Funkcja pobierająca kierunek.
     *
     * @return kierunek
     */
    public Direction getDirection() {
        return direction;
    }
}
