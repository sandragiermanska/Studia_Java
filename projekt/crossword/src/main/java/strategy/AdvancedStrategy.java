package strategy;

import crossword.*;
import database.Entry;

import java.util.LinkedList;
import java.util.Random;

/**
 * Klasa reprezentująca zaawansowaną strategię użytą do wygenerowania krzyżówki.
 *
 * <p>Strategia ta tworzy krzyżówkę z przecinającymi się słowami pionowymi i poziomymi</p>
 *
 * @author Sandra
 */

public class AdvancedStrategy extends Strategy {

    private Crossword crossword;

    /**
     * Funkcja znajdująca kolejne słowa do krzyżówki.
     *
     * <p>Funkcja gdy lista słów w krzyżówce jest równa zero znajduje pierwsze słowo za pomocą
     * odpowiedniej funkcji, a w pozostałych wypadkach znajduje następne krzyżujące się
     * z jakimś poprzednim. Po 20 nieudanych próbach znalezienia następnego słowa kończy wyszukiwanie
     * i zwraca null.</p>
     *
     * @param cw krzyżówka do której dodawane są słowa
     * @return słowo wraz ze współrzędnymi
     */
    @Override
    public CwEntry findEntry(Crossword cw) {
        LinkedList<CwEntry> entries = cw.getEntries();
        CwEntry result;
        if (entries.size() == 0) {
            crossword = cw;
            result = findFirstEntry();
        } else {
            int numberOfTry = 0;
            do {
                result = findNextEntry();
                numberOfTry++;
            } while ((result == null || cw.contains(result.getWord())) && numberOfTry < 20);
            if (numberOfTry == 20) return null;
        }
        return result;
    }

    /**
     * Funkcja znajdująca pierwsze słowo do krzyżówki.
     *
     * <p>Funkcja na podstawie wymiarów tablicy oblicza maksymalną długość słowa i losuje
     * długość w tym zakresie. Jeżeli długość słowa jest mniejsza niż najkrótszy wyraz w słowniku
     * to ją podnosi do tej wartości. Jeżeli długość słowa jest za duża to oznacza, że każdy wyraz
     * się zmieści, więc losuje dowolny.
     * Ustawia wyraz poziomo, z punktem startowym na (1,1).</p>
     *
     * @return słowo wraz ze współrzędnymi
     */
    private CwEntry findFirstEntry() {
        Board board = crossword.getBoard();
        Random random = new Random();

        int maxLength = board.getWidth() - 1; //na krańcach nie może być liter
        int length = random.nextInt(maxLength);
        if (length < 2) length = 2; //minimalna długość wyrazów w słowniku to 2

        Entry entry = crossword.getCwDB().getRandom(length);
        if (entry == null) { //jeżeli nie znaleziono to wylosowana długość za duża więc zmieści się każdy
            entry = crossword.getCwDB().getRandom();
        }

        return new CwEntry(entry, 1, 1, Direction.HORIZ);
    }

    /**
     * Funkcja znajdująca kolejne słowo do krzyżówki.
     *
     * <p>Funkcja losuje wyraz do przecięcia, a potem literę w tym wyrazie. Na podstawie słowa
     * ustawia kierunek nowego słowa. Znajduje maksymalny możliwy do użycia punkt przed literą
     * i za literą, a potem znajduje regex między nimi. Na podstawie wzorca wyszukuje wyraz.
     * Po 20 nieudanych próbach zwraca null. Jeżeli słowo nie występuje już w krzyżówce to znajduje mu
     * punkt początkowy i je zwraca.</p>
     *
     * @return słowo wraz ze współrzędnymi
     */
    private CwEntry findNextEntry() {
        Entry tempEntry = null;
        int whichTry = 0;
        Direction direction;
        Point crossPoint;
        do {
            whichTry++;
            CwEntry crossedEntry = randomEntry();

            if (crossedEntry.getDirection() == Direction.HORIZ) direction = Direction.VERT;
            else direction = Direction.HORIZ;

            crossPoint = findCrossPoint(crossedEntry);
            if (crossPoint == null) continue;

            Point firstPoint = findFirstPointToPattern(crossPoint, direction);
            Point lastPoint = findLastPointToPattern(crossPoint, direction);
            String pattern = crossword.getBoard().createPattern(firstPoint.getX(), firstPoint.getY(), lastPoint.getX(), lastPoint.getY());

            tempEntry = crossword.getCwDB().getRandom(pattern);

        } while ((tempEntry == null || crossword.contains(tempEntry.getWord())) && whichTry < 20);

        if (whichTry == 20) return null;
        Point startPoint = findStartPoint(tempEntry.getWord(), direction, crossPoint);
        return new CwEntry(tempEntry, startPoint.getX(), startPoint.getY(), direction);
    }

    /**
     * Funkcja znajdująca punkt startowy znalezionego słowa.
     *
     * <p>Funkcja pobiera literę z punktu przecięcia i określa pozycję wystąpienia tej litery
     * w znalezionym wyrazie. Sprawdza czy wyraz się mieści, czy jest miejsce przed wyrazem na numerek
     * oraz czy pozostałe litery pasują do juz istniejących. Jeżeli któryś z warunków jest nie spełniony
     * to szuka następnej pozycji danej litery, gdy wszystko się zgadza zwraca punkt startowy.</p>
     *
     * @param word       znalezione słowo
     * @param direction  kierunek znalezionego słowa
     * @param crossPoint punkt przecięcia słów
     * @return punkt startowy
     */
    private Point findStartPoint(String word, Direction direction, Point crossPoint) {
        int horiz = 0, vert = 0;
        if (direction == Direction.HORIZ) horiz = 1;
        else vert = 1;

        int x, y;
        Board board = crossword.getBoard();
        String atCrossPoint = crossword.getBoard().getCell(crossPoint).getContent();
        int startSearch = 0;
        boolean wordPass = false;


        do {
            int index = word.indexOf(atCrossPoint, startSearch);

            //teoretyczny punkt startowy
            x = crossPoint.getX() - index * horiz;
            y = crossPoint.getY() - index * vert;

            //sprawdzenie czy wyraz się mieści
            if (x + word.length() * horiz >= board.getWidth() || y + word.length() * vert >= board.getHeight()) {
                startSearch = index + 1;
                continue;
            }

            //sprawdzenie czy przed wyrazem jest miejce na numerek
            if (board.getCell(x - 1 * horiz, y - 1 * vert).getContent() != null) {
                startSearch = index + 1;
                continue;
            }

            //sprawdzenie czy wyraz pasuje
            int i;
            for (i = 0; i < word.length(); i++) {
                String inCell = board.getCell(x + i * horiz, y + i * vert).getContent();
                if (inCell != null && !inCell.equals(String.valueOf(word.charAt(i)))) break;
            }
            if (i == word.length()) wordPass = true;

            startSearch = index + 1;
        } while (!wordPass);

        return new Point(x, y);
    }

    /**
     * Funkcja znajdująca punkt początkowy do wzorca.
     *
     * <p>Funkcja cofa się od punktu przecięcia względem danego kierunku i sprawdza czy
     * można tam jeszcze poprowadzić słowo(czy pole canCrossNextWord jest równe true).
     * Dopóki można zmienia punkt początkowy. Gdy dojdzie do pola, którego nie można użyć
     * zakończy działanie i zwróci ostatni dobry.</p>
     *
     * @param direction  kierunek znajdywanego słowa
     * @param crossPoint punkt przecięcia słów
     * @return punkt początkowy do wzorca
     */
    private Point findFirstPointToPattern(Point crossPoint, Direction direction) {
        Point result = new Point(crossPoint.getX(), crossPoint.getY());
        int horiz = 0, vert = 0;
        if (direction == Direction.HORIZ) horiz = 1;
        else vert = 1;

        int start = crossPoint.getX() * horiz + crossPoint.getY() * vert;
        int x, y;

        for (int i = start; i > 0; i--) {
            x = crossPoint.getX() * vert + i * horiz;
            y = crossPoint.getY() * horiz + i * vert;
            if (canBeCrossed(x, y)) {
                result.setX(x);
                result.setY(y);
            } else break;
        }
        return result;
    }

    /**
     * Funkcja znajdująca punkt końcowy do wzorca.
     *
     * <p>Funkcja idzie od punktu przecięcia względem danego kierunku i sprawdza czy
     * można tam jeszcze poprowadzić słowo(czy pole canCrossNextWord jest równe true).
     * Dopóki można zmienia punkt końcowy. Gdy dojdzie do pola, którego nie można użyć
     * zakończy działanie i zwróci ostatni dobry.</p>
     *
     * @param direction  kierunek znajdywanego słowa
     * @param crossPoint punkt przecięcia słów
     * @return punkt końcowy do wzorca
     */
    private Point findLastPointToPattern(Point crossPoint, Direction direction) {
        Point result = new Point(crossPoint.getX(), crossPoint.getY());
        int horiz = 0, vert = 0;
        if (direction == Direction.HORIZ) horiz = 1;
        else vert = 1;

        int start = crossPoint.getX() * horiz + crossPoint.getY() * vert;
        int end = crossword.getBoard().getWidth() * horiz + crossword.getBoard().getHeight() * vert;
        int x, y;

        for (int i = start; i < end; i++) {
            x = crossPoint.getX() * vert + i * horiz;
            y = crossPoint.getY() * horiz + i * vert;
            if (canBeCrossed(x, y)) {
                result.setX(x);
                result.setY(y);
            } else break;
        }
        return result;
    }

    /**
     * Funkcja losująca wyraz z już istniejących w krzyżówce.
     *
     * @return wyraz z krzyżówki
     */
    private CwEntry randomEntry() {
        Random random = new Random();
        int numberOfEntries = crossword.getEntries().size();
        return crossword.getEntry(random.nextInt(numberOfEntries));
    }

    /**
     * Funkcja losująca punkt przecięcia z danego wyrazu.
     *
     * <p>Funkcja znajduje wszystkie możliwe do użycia pola w danym słowie i losuje
     * z nich jeden punkt przecięcia. Jeżeli nie ma żadnego dostępnego pola zwraca null.</p>
     *
     * @param crossedEntry słowo do przecięcia
     * @return punkt przecięcia się wyrazów
     */
    private Point findCrossPoint(CwEntry crossedEntry) {
        Random random = new Random();

        int horiz = 0, vert = 0;
        if (crossedEntry.getDirection() == Direction.HORIZ) horiz = 1;
        else vert = 1;

        int x = crossedEntry.getFirstPosX();
        int y = crossedEntry.getFirstPosY();


        LinkedList<Point> pointsWhichCanBeCrossPoints = new LinkedList<>();
        for (int i = 0; i < crossedEntry.getWord().length(); i++) {
            int currentX = x + i * horiz;
            int currentY = y + i * vert;
            if (canBeCrossed(currentX, currentY)) {
                pointsWhichCanBeCrossPoints.add(new Point(currentX, currentY));
            }
        }

        if (pointsWhichCanBeCrossPoints.size() == 0) return null;

        int index = random.nextInt(pointsWhichCanBeCrossPoints.size());
        return pointsWhichCanBeCrossPoints.get(index);
    }

    /**
     * Funkcja sprawdzająca czy przez pole może przechodzić wyraz.
     *
     * @return czy przez pole może przechodzić wyraz
     */
    private boolean canBeCrossed(int x, int y) {
        return crossword.getBoard().getCell(x, y).canCrossNextWord();
    }

    /**
     * Funkcja aktualizująca tablicę krzyżówki.
     *
     * <p>Funkcja ustawia pola przed i za słowem jako już niedostępne, a potem iteruje po tablicy
     * i ustawia ich zawarości w jeszcze pustych miejscach. Jeżeli natomiast to miejsce jest już
     * zajęte to oznacza punkt przecięcia i ustawia pola na około tego punktu oraz w nim jako już
     * nie dostępne.</p>
     *
     * @param board tablica krzyżówki
     * @param entry słowo dodawane do krzyżówki
     */
    @Override
    public void updateBoard(Board board, CwEntry entry) {
        String word = entry.getWord();

        int horiz = 0, vert = 0;
        if (entry.getDirection() == Direction.HORIZ) horiz = 1;
        else vert = 1;

        setInaccessiblePlacesBeforeEntry(entry.getFirstPoint(), horiz, vert);

        setInaccessiblePlacesAfterEntry(entry.getFirstPosX() + (word.length() - 1) * horiz,
                entry.getFirstPosY() + (word.length() - 1) * vert, horiz, vert);

        int x, y;
        //iterować będzie tylko po jednej współrzędnej
        for (int i = 0; i < word.length(); i++) {
            x = entry.getFirstPosX() + i * horiz;
            y = entry.getFirstPosY() + i * vert;
            if (board.getCell(x, y).getContent() == null) { //była wolna więc dodajemy
                board.getCell(x, y).setContent(String.valueOf(word.charAt(i)));
            } else { //była zajęta więc się krzyżują
                setInaccessiblePlacesInCrossPoint(x, y);
            }
        }
    }

    /**
     * Funkcja ustawiająca pola przed słowem jako niedostępne.
     *
     * @param startPoint punkt starowy słowa
     * @param horiz      równe 1 gdy poziome, a 0 gdy pionowe
     * @param vert       równe 0 gdy poziome, a 1 gdy pionowe
     */
    private void setInaccessiblePlacesBeforeEntry(Point startPoint, int horiz, int vert) {
        Board board = crossword.getBoard();

        int x = startPoint.getX();
        int y = startPoint.getY();

        board.getCell(x - 1, y - 1).setCanCrossNextWord(false); //lewa,górna
        board.getCell(x - horiz, y - vert).setCanCrossNextWord(false); //przed
        board.getCell(x + vert - horiz, y - vert + horiz).setCanCrossNextWord(false); //prawa,górna / lewa,dolna
    }

    /**
     * Funkcja ustawiająca pola wokół przecięcia oraz przecięcia jako niedostępne.
     *
     * @param x współrzędna x punktu przecięcia
     * @param y współrzędna y punktu przecięcia
     */
    private void setInaccessiblePlacesInCrossPoint(int x, int y) {
        Board board = crossword.getBoard();

        //komórki na około
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                board.getCell(x + i, y + j).setCanCrossNextWord(false);
            }
        }

    }

    /**
     * Funkcja ustawiająca pola za słowem jako niedostępne.
     *
     * @param x     współrzędna x ostatniego punktu słowa
     * @param y     współrzędna y ostatniego punktu słowa
     * @param horiz równe 1 gdy poziome, a 0 gdy pionowe
     * @param vert  równe 0 gdy poziome, a 1 gdy pionowe
     */
    private void setInaccessiblePlacesAfterEntry(int x, int y, int horiz, int vert) {
        Board board = crossword.getBoard();

        board.getCell(x + 1, y + 1).setCanCrossNextWord(false); //prawa,dolna
        board.getCell(x + horiz, y + vert).setCanCrossNextWord(false); //za
        board.getCell(x - vert + horiz, y + vert - horiz).setCanCrossNextWord(false); //lewa,dolna / prawa,górna
    }

}
