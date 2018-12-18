package lab9;

public class Levenshtein {

    public static int odlegloscLevenshteina(String orginal, String copy) {
        int orginalSize = orginal.length();
        int copySize = copy.length();
        int[][] d = new int[orginalSize+1][copySize+1];
        for (int i = 0; i <= orginalSize; i++) {
            d[i][0] = i;
        }
        for (int i = 1; i <= copySize; i++) {
            d[0][i] = i;
        }

        int c;
        for (int i = 1; i <= orginalSize; i++) {
            for (int j = 1; j <= copySize; j++) {
                if (orginal.charAt(i-1) == copy.charAt(j-1)) {
                    c = 0;
                } else {
                    c = 1;
                }
                d[i][j] = Math.min(Math.min(d[i-1][j] + 1, d[i][j-1] + 1), d[i-1][j-1] + c);
            }
        }
        return d[orginalSize][copySize];
    }
}
