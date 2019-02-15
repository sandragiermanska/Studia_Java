package database;

import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasa pochodna bazy danych haseł do krzyżówek.
 *
 * <p>Pochodna klasa bazy danych odpowiedzialna za generowanie losowego hasła
 * ze słownika.
 * Zapisuje wyrazy w słowniku z sposób alfabetyczny.</p>
 *
 * @author Sandra
 */

public class InteliCrosswordDB extends CrosswordDB {

    /**
     * Konstruktor klasy wywołujący konstruktor klasy bazowej.
     *
     * @param filename ścieżka do pliku z hasłami do krzyżówki
     */
    public InteliCrosswordDB(String filename) {
        super(filename);
    }

    /**
     * Funkcja znajdująca wszystkie hasła ze słownika pasujące do podanego wzorca.
     *
     * @param pattern wzorzec hasła
     * @return lista obiektów Entry pasujących do wzorca
     */
    public LinkedList<Entry> findAll(String pattern) {
        LinkedList<Entry> list = new LinkedList<>();
        Pattern p = Pattern.compile(pattern);
        Matcher matcher;
        for (int i = 0; i < dictionary.size(); i++) {
            Entry entry = dictionary.get(i);
            matcher = p.matcher(entry.getWord());
            if (matcher.matches()) {
                list.add(entry);
            }
        }
        return list;
    }

    /**
     * Funkcja zwracająca losowe hasło ze słownika.
     *
     * @return losowy obiekt Entry
     */
    public Entry getRandom() {
        Random random = new Random();
        int size = dictionary.size();
        return dictionary.get(random.nextInt(size));
    }

    /**
     * Funkcja zwracająca losowe hasło ze słownika o podanej długości.
     * Jeżeli nie ma hasła o podanej długości zwraca null.
     *
     * @param length długość słowa do krzyżówki
     * @return losowy obiekt Entry o podanej długości lub null
     */
    public Entry getRandom(int length) {
        LinkedList<Entry> list = new LinkedList<>();
        int size = dictionary.size();
        for (int i = 0; i < size; i++) {
            Entry entry = dictionary.get(i);
            if (entry.getWord().length() == length) {
                list.add(entry);
            }
        }
        Random random = new Random();
        size = list.size();
        if (size == 0) return null;
        return list.get(random.nextInt(size));
    }

    /**
     * Funkcja zwracająca losowe hasło ze słownika o pasujące do danego wzorca.
     * Jeżeli nie ma pasującego hasła zwraca null.
     *
     * @param pattern wzorzec słowa do krzyżówki
     * @return losowy obiekt Entry pasujący do wzorca lub null
     */
    public Entry getRandom(String pattern) {
        LinkedList<Entry> list = findAll(pattern);
        Random random = new Random();
        int size = list.size();
        if (size == 0) return null;
        return list.get(random.nextInt(size));
    }

    /**
     * Funkcja dodająca jedno hasło do słownika bazy danych w porządku alfabetycznym.
     *
     * @param word słowo do krzyżówki
     * @param clue pytanie do słowa
     */
    @Override
    public void add(String word, String clue) {
        Entry entry = new Entry(word, clue);
        int size = dictionary.size();
        int i;
        for (i = 0; i < size; i++) {
            String currentWord = dictionary.get(i).getWord();
            if (word.compareToIgnoreCase(currentWord) < 0) {
                break;
            }
        }
        dictionary.add(i, entry);
    }
}