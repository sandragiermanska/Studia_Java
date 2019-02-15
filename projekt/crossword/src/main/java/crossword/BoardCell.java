package crossword;

import java.io.Serializable;

/**
 * Klasa reprezentująca komórkę w tablicy krzyżówki.
 * <p>Posiada literę, którą ma zawierać pole
 * oraz informację czy może tam występować kolejne słowo krzyżówki</p>
 *
 * @author Sandra
 */

public class BoardCell implements Serializable {

    private String content;
    private boolean canCrossNextWord;

    /**
     * Konstruktor przyjmujący literę jako String. Domyślnie ustawia,
     * że może występować tam kolejne słowo krzyżówki.
     *
     * @param content zawartość pola krzyżówki (litera)
     */
    public BoardCell(String content) {
        this.content = content;
        canCrossNextWord = true;
    }

    /**
     * Metoda ustawiająca zawartość pola.
     *
     * @param content litera wewnątrz pola
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Metoda pobierająca zawartość pola.
     *
     * @return litera wewnątrz pola
     */
    public String getContent() {
        return content;
    }

    /**
     * Metoda ustawiająca informację czy może tam występować kolejne słowo krzyżówki.
     *
     * @param canCrossNextWord informacja czy może tam być kolejne słowo
     */
    public void setCanCrossNextWord(boolean canCrossNextWord) {
        this.canCrossNextWord = canCrossNextWord;
    }

    /**
     * Metoda pobierająca informację czy może tam występować kolejne słowo krzyżówki.
     *
     * @return czy może tam być kolejne słowo
     */
    public boolean canCrossNextWord() {
        return canCrossNextWord;
    }
}