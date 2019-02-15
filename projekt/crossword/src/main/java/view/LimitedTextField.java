package view;

import java.util.Objects;

import crossword.Point;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/** Klasa dziedzicząca po TextField mająca dodatkowo maksymalną ilość znaków jaką może
 * zawierać pole tekstowe, punkt gdzie się znajduje na siatce krzyżówki oraz funkcje jakie
 * ma wykonać po wpisaniu litery i po kliknięciu na pole tekstowe.
 */

public class LimitedTextField extends TextField {

    private final IntegerProperty maxLength;
    private Executeable onKeyPressed;
    private Executeable onClick;
    private Point pointOnBoard;

    /**
     * Konstruktor bezparametrowy
     */
    public LimitedTextField() {
        super();
        this.maxLength = new SimpleIntegerProperty(-1);
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onClickHandler();
            }
        });
    }

    /**
     * Funkcja wywołująca funkcję po kliknięciu na pole tekstowe.
     */
    public void onClickHandler() {
        onClick.execute(this);
    }

    /**
     * Funkcja zwracająca pozycję na siatce krzyżówki.
     *
     * @return punkt na siatce krzyżówki
     */
    public Point getPointOnBoard() {
        return pointOnBoard;
    }

    /**
     * Funkcja ustawiająca pozycję na siatce krzyżówki.
     *
     * @param pointOnBoard punkt na siatce krzyżówki
     */
    public void setPointOnBoard(Point pointOnBoard) {
        this.pointOnBoard = pointOnBoard;
    }

    /**
     * Funkcja ustawiająca funkcję wywoływaną po wpisaniu litery.
     *
     * @param executeable funkcja wywoływana po wpisaniu litery
     */
    public void setOnKeyPressed(Executeable executeable) {
        this.onKeyPressed = executeable;
    }

    /**
     * Funkcja ustawiająca funkcję wywoływaną po kliknięciu na pole.
     *
     * @param executeable funkcja wywoływana po kliknięciu na pole
     */
    public void setOnClick(Executeable executeable) {
        this.onClick = executeable;
    }


    /**
     * Funkcja zwracająca maksymalną ilość znaków przyjmowaną przez pole tekstowe.
     *
     * @return ilość znaków
     */
    public final Integer getMaxLength() {
        return this.maxLength.getValue();
    }

    /**
     * Funkcja zwracająca maksymalną ilość znaków przyjmowaną przez pole tekstowe.
     *
     * @param maxLength ilość znaków
     */
    public final void setMaxLength(Integer maxLength) {
        Objects.requireNonNull(maxLength, "Max length cannot be null, -1 for no limit");
        this.maxLength.setValue(maxLength);
    }

    /**
     * Funkcja wywoływana podczas wpisywania jakiegoś tekstu. Zapobiega wpisaniu
     * większej ilości znaków niż dozwolona, zamienia litery na wielkie oraz
     * wywołuje funkcję po wpisaniu znaku do pola.
     *
     * @param start        początek przedziału do zamienienia
     * @param end          koniec przedziału do zamienienia
     * @param insertedText wprowadzony tekst
     */
    @Override
    public void replaceText(int start, int end, String insertedText) {
        if (this.getMaxLength() <= 0) {
            // Default behavior, in case of no max length
            super.replaceText(start, end, insertedText);
        } else {
            // Get the text in the textfield, before the user enters something
            String currentText = this.getText() == null ? "" : this.getText();

            // Compute the text that should normally be in the textfield now
            String finalText = currentText.substring(0, start) + insertedText + currentText.substring(end);

            // If the max length is not excedeed
            int numberOfexceedingCharacters = finalText.length() - this.getMaxLength();
            if (numberOfexceedingCharacters <= 0) {
                // Normal behavior
                super.replaceText(start, end, insertedText.toUpperCase());
            } else {
                // Otherwise, cut the the text that was going to be inserted
                String cutInsertedText = insertedText.substring(
                        0,
                        insertedText.length() - numberOfexceedingCharacters
                );

                // And replace this text
                super.replaceText(start, end, cutInsertedText.toUpperCase());
            }
        }
        if (!insertedText.isEmpty()) {
            onKeyPressed.execute(this);
        }
    }

}