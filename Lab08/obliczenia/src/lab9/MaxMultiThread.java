package lab9;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class MaxMultiThread implements Callable<ArrayList<Integer>> {

    private String numbers;
    private Function function;

    MaxMultiThread(String numbers, Function function) {
        this.numbers = numbers;
        this.function = function;
    }

    @Override
    public ArrayList<Integer> call() throws Exception {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < this.numbers.length(); i++) {
            numbers.add(Character.getNumericValue(this.numbers.charAt(i)));
        }
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0;
        int size = numbers.size();
        while (!Thread.currentThread().isInterrupted() && i < size) {
            result.add(function.function(numbers.get(i)));
            i++;
        }
        return result;
    }
}

interface Function {
    int function(int number);
}