import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrosswordDB {

    protected LinkedList<Entry> dictionary = new LinkedList<>();

    public CrosswordDB(String filename) {
        createDB(filename);
    }

    public void add(String word, String clue) {
        Entry entry = new Entry(word, clue);
        dictionary.add(entry);
    }

    public Entry get(String word) {
        for (int i = 0; i < dictionary.size(); i++) {
            if(dictionary.get(i).getWord().equals(word)) {
                return dictionary.get(i);
            }
        }
        return null;
    }

    public void remove(String word) {
        for (int i = 0; i < dictionary.size(); i++) {
            if(dictionary.get(i).getWord().equals(word)) {
                dictionary.remove(i);
                break;
            }
        }
    }

    public void saveDB(String filename) {
        //=============================================????????????????????????????????
    }

    public int getSize() {
        return dictionary.size();
    }

    protected void createDB(String filename) {
        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)) {
            //int i = 0;
            String word, clue, text;
            Pattern pattern = Pattern.compile("\\[(.*)\\] (.*)");
            Matcher matcher;
            while(scanner.hasNextLine()) {
                word = scanner.nextLine();
                text = scanner.nextLine();
                matcher = pattern.matcher(text);
                if (matcher.matches()) {
                    clue = matcher.group(2);
                } else {
                    clue = text;
                }
                add(word,clue);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
