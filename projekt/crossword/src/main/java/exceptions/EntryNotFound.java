package exceptions;

/**
 * Wyjątek wyrzucany gdy nie znaleziono pasującego słowa do krzyżówki
 *
 * <p>
 * Wyrzucany tylko w sytuacji krzyżówki poziomej, jeżeli nie znaleziono
 * żadnego słowa w słowniku, żeby krzyżowało się z daną literą hasła.
 * </p>
 *
 * @author Sandra
 */

public class EntryNotFound extends Exception {}
