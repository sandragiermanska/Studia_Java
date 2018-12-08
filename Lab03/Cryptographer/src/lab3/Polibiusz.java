package lab3;

public class Polibiusz implements Algorithm {

    static final char[][] table = {{'a','b','c','d','e'},{'f','g','h','i','k'},
            {'l','m','n','o','p'},{'q','r','s','t','u'},{'v','w','x','y','z'}};

    @Override
    public String crypt(String text) {
        int size = table.length;
        String result = text;
        result = result.toLowerCase();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String number = Integer.toString(i+1) + Integer.toString(j+1);
                if (i == 1 && j == 3) {
                    result = result.replaceAll("j", number);
                }
                result = result.replaceAll(Character.toString(table[i][j]), number);

            }
        }
        return result;
    }

    @Override
    public String decrypt(String text) {
        int size = text.length() / 2;
        String result = text;
        for (int i = 0; i < size; i++) {
            int numberRow = Integer.parseInt(Character.toString(result.charAt(i))) - 1;
            int numberCol = Integer.parseInt(Character.toString(result.charAt(i+1))) - 1;
            result = result.substring(0,i)+table[numberRow][numberCol]+result.substring(i+2);
        }
        return result;
    }
}

