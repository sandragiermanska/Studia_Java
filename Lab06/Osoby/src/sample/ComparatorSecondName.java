package sample;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComparatorSecondName implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        Pattern pattern = Pattern.compile("([\\wĄąĘęĆćŁłŃńÓóŚśŹźŻż]+) ([\\wĄąĘęĆćŁłŃńÓóŚśŹźŻż]+) ([\\wĄąĘęĆćŁłŃńÓóŚśŹźŻż]+)");
        Matcher matcher = pattern.matcher(person1.getFullName());
        String person1Name, person2Name;
        if (matcher.find()) {
            person1Name = matcher.group(2);
        }
        else {
            person1Name = "";
        }
        matcher = pattern.matcher(person2.getFullName());
        if (matcher.find()) {
            person2Name = matcher.group(2);
        }
        else {
            person2Name = "";
        }
        return person1Name.compareTo(person2Name);
    }
}
