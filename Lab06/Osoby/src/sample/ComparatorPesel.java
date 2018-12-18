package sample;

import java.util.Comparator;

public class ComparatorPesel implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        return person1.getPesel().compareTo(person2.getPesel());
    }
}
