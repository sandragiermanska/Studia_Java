package sample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class DB implements Iterable<Person> {

    private ArrayList<Person> data = new ArrayList<>();

    public void setData(ArrayList<Person> data) {
        this.data = data;
    }

    public void addPerson(Person person) {
        data.add(person);
    }

    public void remove(Person person) {
        data.remove(person);
    }

    public void remove(int index) {
        if (index < data.size()) {
            data.remove(index);
        }
    }

    public int size() {
        return data.size();
    }

    public Person get(int index) {
        if (index < data.size()) {
            return data.get(index);
        }
        return null;
    }

    @Override
    public Iterator<Person> iterator() {
        return data.iterator();
    }

    public void sort(Comparator<Person> c) {
        data.sort(c);
    }














}
