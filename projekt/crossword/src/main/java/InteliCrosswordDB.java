import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InteliCrosswordDB extends CrosswordDB {

    public InteliCrosswordDB(String filename) {
        super(filename);
    }

    public LinkedList<Entry> findAll(String pattern) {
        LinkedList<Entry> list = new LinkedList<>();
        Pattern p = Pattern.compile(pattern);
        Matcher matcher;
        for (int i = 0; i < dictionary.size(); i++) {
            Entry entry= dictionary.get(i);
            matcher = p.matcher(entry.getWord());
            if(matcher.matches()) {
                list.add(entry);
            }
        }
        return list;
    }

    public Entry getRandom() {
        Random random = new Random();
        int size = dictionary.size();
        return dictionary.get(random.nextInt(size));
    }

    public Entry getRandom(int length) {
        LinkedList<Entry> list = new LinkedList<>();
        int size = dictionary.size();
        for (int i = 0; i < size; i++) {
            Entry entry= dictionary.get(i);
            if(entry.getWord().length() == length) {
                list.add(entry);
            }
        }
        Random random = new Random();
        size = list.size();
        if (size == 0) return null;
        return list.get(random.nextInt(size));
    }

    public Entry getRandom(String pattern) {
        LinkedList<Entry> list = findAll(pattern);
        Random random = new Random();
        int size = list.size();
        if (size == 0) return null;
        return list.get(random.nextInt(size));
    }

    @Override
    public void add(String word, String clue) {
        Entry entry = new Entry(word, clue);
        int size = dictionary.size();
        int i;
        for (i = 0; i < size; i++) {
            String currentWord = dictionary.get(i).getWord();
            if(word.compareToIgnoreCase(currentWord) < 0) {
                break;
            }
        }
        dictionary.add(i, entry);
    }
}
