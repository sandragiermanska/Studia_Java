package sample;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComparatorBirthdate implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        Pattern pattern = Pattern.compile("(\\d{2})(\\d{2})(\\d{2})");
        Matcher matcher = pattern.matcher(person1.getPesel());
        String person1Date, person2Date;
        if (matcher.find()) {
            int month = Integer.parseInt(matcher.group(2));
            if (month > 20 && month < 33) {
                month -= 20;
                person1Date = "20" + matcher.group(1) + month + matcher.group(3);
            } else if (month < 13) {
                person1Date = "19" + matcher.group(1) + month + matcher.group(3);
            } else {
                person1Date = "";
            }
        }
        else {
            person1Date = "";
        }
        matcher = pattern.matcher(person2.getPesel());
        if (matcher.find()) {
            int month = Integer.parseInt(matcher.group(2));
            if (month > 20 && month < 33) {
                month -= 20;
                person2Date = "20" + matcher.group(1) + month + matcher.group(3);
            } else if (month < 13) {
                person2Date = "19" + matcher.group(1) + month + matcher.group(3);
            } else {
                person2Date = "";
            }
        }
        else {
            person2Date = "";
        }
        return -1 * person1Date.compareTo(person2Date);
    }
}
