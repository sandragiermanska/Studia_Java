package strategy;

import crossword.*;
import database.Entry;
import exceptions.EntryNotFound;

import java.util.LinkedList;

/**
 * Klasa reprezentująca prostą strategię użytą do wygenerowania krzyżówki.
 *
 * <p>Strategia ta tworzy krzyżówkę z pionowym hasłem i przecinającymi go pozostałymi słowami</p>
 *
 * @author Sandra
 */

public class SimpleStrategy extends Strategy {

    /**
     * Funkcja znajdująca kolejne słowa do krzyżówki.
     *
     * <p>Funkcja gdy lista słów w krzyżówce jest równa zero znajduje hasło,
     * a w pozostałych wypadkach oblicza indeks danej litery w haśle i wyszukuje słowo
     * mogące ją przeciąć, jeżeli słowo już występuje w krzyżówce próbuje ponownie.
     * Po 20 próbach zakończonych porażką wyrzuca wyjątek EntryNotFound.</p>
     *
     * @param cw krzyżówka do której dodawane są słowa
     * @return kolejne słowo wraz ze współrzędnymi
     * @throws EntryNotFound wyjątek wyrzucany gdy nie udaje sę odnaleźć słowa potrzebnego do wygenerowania krzyżówki
     */
    @Override
    public CwEntry findEntry(Crossword cw) throws EntryNotFound {
        LinkedList<CwEntry> entries = cw.getEntries();
        CwEntry result;
        if (entries.size() == 0) {
            result = findPassword(cw);
        } else {
            CwEntry password = entries.get(0);
            int numberOfCurrentLetterFromPassword = entries.size() - 1;
            if (numberOfCurrentLetterFromPassword == password.getWord().length()) return null;

            int numberOfTry = 0;
            do {
                result = findCurrentEntry(cw, numberOfCurrentLetterFromPassword);
                numberOfTry++;
            } while (cw.contains(result.getWord()) && numberOfTry < 20);
            if (numberOfTry == 20) throw new EntryNotFound();
        }
        return result;
    }

    /**
     * Funkcja znajdująca hasło krzyżówki.
     *
     * <p>Funkcja wylicza maksymalną długość hasła na podstawie wymiarów krzyżówki
     * i wyszukuje hasło o danej długości. Jezeli nie znajduje wyrazu o danej długości zmniejsza
     * ją o 1 i wyszukuje ponownie. Jeżeli zejdzie do zera wyrzuca wyjątek. Ustawia współrzędne
     * punktu początkowego na połowę szerokości i pierwsze od góry nie licząc krawędzi.</p>
     *
     * @param crossword krzyżówka do której dodawane są słowa
     * @return hasło wraz ze współrzędnymi
     * @throws EntryNotFound wyjątek wyrzucany gdy nie udaje sę odnaleźć hasła
     */
    private CwEntry findPassword(Crossword crossword) throws EntryNotFound {
        int lengthOfPassword = crossword.getBoard().getHeight() - 2; //ne może być na krańcach
        int width = crossword.getBoard().getWidth();
        Entry tempEntry = null;
        while (tempEntry == null) {
            tempEntry = crossword.getCwDB().getRandom(lengthOfPassword);
            lengthOfPassword--;
            if (lengthOfPassword == 0) throw new EntryNotFound();
        }
        CwEntry result = new CwEntry(tempEntry, width/2, 1, Direction.VERT);
        result.setVisible(false); //nie jest widoczny numerek i pytanie
        return result;
    }

    /**
     * Funkcja znajdująca słowo przecinające hasło.
     *
     * <p>Funkcja wylicza lewy i prawy punkt maksymalnego zakresu hasła i wyszukuje wzorzec
     * odpowiadający sytuacji w tablicy krzyżówki między tymi punktami. Wyszukuje losowe słowo
     * odpowiadające wzorcowi i ustawia punkt startowy słowa. Jeżeli nie może znaleźć żadnego
     * wyrzuca wyjątek.
     *
     * @param cw krzyżówka do której dodawane są słowa
     * @param numberOfLetter indeks litery w haśle
     * @return hasło wraz ze współrzędnymi
     * @throws EntryNotFound wyjątek wyrzucany gdy nie udaje sę odnaleźć hasła
     */
    private CwEntry findCurrentEntry(Crossword cw, int numberOfLetter) throws EntryNotFound {
        int from = 1; //zostawione miejsce na numerek pytania
        int to = cw.getBoard().getWidth() - 2; //na końcu nie może być

        //w pierwszej lini nie ma więc +1
        String pattern = cw.getBoard().createPattern(from, numberOfLetter + 1, to, numberOfLetter + 1);
        Entry tempEntry = cw.getCwDB().getRandom(pattern);
        if (tempEntry == null) throw new EntryNotFound();
        Point startPoint = findStartPoint(tempEntry, cw, numberOfLetter);
        return new CwEntry(tempEntry, startPoint.getX(), startPoint.getY(), Direction.HORIZ);
    }

    /**
     * Funkcja znajdująca punkt startowy znalezionego słowa.
     *
     * <p>Funkcja znajduje literę którą przecina znaleziony wyraz i szuka pozycji
     * tej litery w nowym wyrazie oraz sprawdza czy reszta wyrazu zmieści się w krzyżówce
     * (w wypadku gdy jest więcej razy użyta litera z hasła w wyrazie nie wiemy do krótej został
     * dopasowany wzorzec).</p>
     *
     * @param entry znalezione słowo
     * @param crossword krzyżówka do której dodawane są słowa
     * @param numberOfLetter indeks litery w haśle
     * @return punkt startowy
     */
    private Point findStartPoint(Entry entry, Crossword crossword, int numberOfLetter) {
        int width = crossword.getBoard().getWidth();
        CwEntry password = crossword.getEntry(0);
        char letter = password.getWord().charAt(numberOfLetter);
        int crossPointX = password.getFirstPosX();

        String word = entry.getWord();

        int j;
        for (j = 0; j < word.length(); j++) {
            //jeżeli litera się zgadza i reszta wyrazu mieści się w krzyżówce
            if (word.charAt(j) == letter && word.length() - j <= width - crossPointX) {
                break;
            }
        }

        return new Point(crossPointX - j, numberOfLetter + 1); //bo w pierwszej lini(y) nie ma nic
    }

    /**
     * Funkcja aktualizująca tablicę krzyżówki.
     *
     * <p>Funkcja pobiera słowo, punkt startowy i kierunek, a potem iteruje po tablicy
     * i ustawia nowe komórki w odpowiednich miejscach.</p>
     *
     * @param b tablica krzyżówki
     * @param e słowo dodawane do krzyżówki
     */
    @Override
    public void updateBoard(Board b, CwEntry e) {
        String word = e.getWord();

        int horiz = 0, vert = 0;
        if (e.getDirection() == Direction.HORIZ) {
            horiz = 1;
        } else {
            vert = 1;
        }

        //iterować będzie tylko po jednej współrzędnej
        for (int i = 0; i < word.length(); i++) {
            BoardCell cell = new BoardCell(String.valueOf(word.charAt(i)));
            b.setCell(e.getFirstPosX() + i*horiz, e.getFirstPosY() + i*vert, cell);
        }
    }
}