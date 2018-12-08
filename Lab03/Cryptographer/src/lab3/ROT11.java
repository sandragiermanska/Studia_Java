package lab3;

import java.util.ArrayList;
import java.util.Arrays;

public class ROT11 implements Algorithm {

    static final ArrayList<Character> alfabet = new ArrayList<>();
    static final int move = 11;

    public ROT11() {
        alfabet.addAll(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
    }

    @Override
    public String crypt(String text) {
        int size = text.length();
        String result = text;
        for (int i = 0; i < size; i++) {
            int index = alfabet.indexOf(text.charAt(i));
            int newIndex = (index + move) % alfabet.size();
            result = result.substring(0,i)+alfabet.get(newIndex)+result.substring(i+1);//  .getChars(i,i);]//. charAt(i) = alfabet.get(newIndex);
        }
        return result;
    }

    @Override
    public String decrypt(String text) {
        int size = text.length();
        String result = text;
        for (int i = 0; i < size; i++) {
            int index = alfabet.indexOf(text.charAt(i));
            int newIndex = (index - move + alfabet.size()) % alfabet.size();
            result = result.substring(0,i)+alfabet.get(newIndex)+result.substring(i+1);//  .getChars(i,i);]//. charAt(i) = alfabet.get(newIndex);
        }
        return result;
    }
}


