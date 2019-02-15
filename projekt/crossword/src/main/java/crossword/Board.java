package crossword;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Klasa reprezentująca tablicę krzyżówki z literą.
 *
 * <p>Zawiera tablicę dwuwymiarową obiektów BoardCell, gdzie punkt (0,0)
 * jest w lewym górnym rogu, a współrzędne rosną w dół i w prawo.
 * Odpowiada też za tworzenie wzorców haseł bazując na aktualnym stanie tablicy.</p>
 *
 * @author Sandra
 */

public class Board implements Serializable {

    private BoardCell[][] board;

    /**
     * Konstruktor klasy tworzący tablicę o podanych wymiarach
     * oraz zabezpieczający krawędzie, żeby nie mogło tam być żadnego
     * słowa.
     *
     * @param width  szerokość tablicy łącznie z krawędziami
     * @param height wysokość tablicy łącznie z krawędziami
     */
    public Board(int width, int height) {
        board = new BoardCell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = new BoardCell(null);

                //krawędzie - nie mogą mieć w sobie haseł
                if (i == 0 || i == width - 1 || j == 0 || j == height - 1) {
                    board[i][j].setCanCrossNextWord(false);
                }
            }
        }
    }

    /**
     * Funkcja pobierająca szerokość tablicy łącznie z krawędziami.
     *
     * @return szerokość tablicy
     */
    public int getWidth() {
        return board.length;
    }

    /**
     * Funkcja pobierająca wysokość tablicy łącznie z krawędziami.
     *
     * @return wysokość tablicy
     */
    public int getHeight() {
        return board[0].length;
    }

    /**
     * Funkcja do pobrania komórki z tablicy.
     * Jeżeli współrzędne wychodzą poza rozmiar tablicy to zwraca null.
     *
     * @param x współrzędna x (po szerokości)
     * @param y współrzędna y (po wysokości)
     * @return obiekt BoardCell lub null
     */
    public BoardCell getCell(int x, int y) {
        if (x < getWidth() && y < getHeight()) {
            return board[x][y];
        }
        return null;
    }

    /**
     * Funkcja do pobrania komórki z tablicy.
     * Jeżeli punkt jest poza rozmiarem tablicy to zwraca null.
     *
     * @param point punkt na tablicy
     * @return obiekt BoardCell lub null
     */
    public BoardCell getCell(Point point) {
        return board[point.getX()][point.getY()];
    }

    /**
     * Funkcja do ustawienia komórki w tablicy.
     * Jeżeli współrzędne wychodzą poza rozmiar tablicy to tablica się nie zmieni.
     *
     * @param x współrzędna x (po szerokości)
     * @param y współrzędna y (po wysokości)
     * @param c nowa komórka do ustawienia w danym punkcie tablicy
     */
    public void setCell(int x, int y, BoardCell c) {
        if (x < getWidth() && y < getHeight()) {
            board[x][y] = c;
        }
    }

    /**
     * Funkcja tworząca wzorzec słowa na podstawie współrzędnych dwóch punktów:
     * maksymalnie początkowego i maksymalnie końcowego.
     * Wzorzec zawiera wszystkie litery wewnątrz przedziału, ale przed pierwszą
     * i za ostatnią literą przyjmuje przedział znaków od zera do maksymalnej ilości liter,
     * które się tam zmieszczą.
     * Jeżeli punkty nie są w jednej linii (pionowej lub poziomej) zwraca null.
     *
     * @param fromX współrzędna x punktu startowego
     * @param fromY współrzędna y punktu startowego
     * @param toX   współrzędna x punktu końcowego
     * @param toY   współrzędna y punktu końcowego
     * @return wzorzec nowego słowa
     */
    public String createPattern(int fromX, int fromY, int toX, int toY) {
        StringBuilder pattern = new StringBuilder();

        if (fromX != toX && fromY != toY) {
            return null;
        }

        ArrayList<Integer> spaceBetweenLetters = new ArrayList<>();
        ArrayList<String> letters = new ArrayList<>();
        int space = 0;

        //w zależności od kierunku hasła jedna z pętli wykona się tylko raz
        for (int i = fromX; i <= toX; i++) {
            for (int j = fromY; j <= toY; j++) {
                if (board[i][j].getContent() != null) {
                    letters.add(board[i][j].getContent());
                    spaceBetweenLetters.add(space);
                    space = 0;
                } else {
                    space++;
                }
            }
        }
        spaceBetweenLetters.add(space);

        //pierwsza przestrzeń - możliwe litery przed
        pattern.append("^.{0,");
        pattern.append(spaceBetweenLetters.get(0));
        pattern.append("}");

        //pierwsza litera
        pattern.append(letters.get(0));

        //dodawanie kolejnych przestrzeni i liter
        int size = letters.size();
        for (int i = 1; i < size; i++) {
            pattern.append(".{");
            pattern.append(spaceBetweenLetters.get(i));
            pattern.append("}");
            pattern.append(letters.get(i));
        }

        //ostatnia przestrzeń - możliwe litery za
        pattern.append(".{0,");
        pattern.append(spaceBetweenLetters.get(size)); //bo przestrzeni zawsze o jeden więcej niż liter
        pattern.append("}$");

        return pattern.toString();
    }
}