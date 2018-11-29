package lab2;

import java.util.ArrayList;
import java.util.List;

public class DataFrame {
    private ArrayList<ArrayList> df = new ArrayList();
    private ArrayList<String> names = new ArrayList();

    public DataFrame() {}

    public DataFrame(String names, String types) {
        int size = 1;
        Class<?> class_def = null;
        try {
            class_def = Class.forName(types);
        } catch (Exception e) {}
        ArrayList<Class<?>> kol = new ArrayList<>();
        df.set(0, kol);
        this.names.set(0, names);

    }

    public DataFrame(String[] names, String[] types) {
        int size = -1;
        if (names.length == types.length) {
            size = names.length;
        }
        for (int i = 0; i < size; i++) {
            Class<?> class_def = null;
            try {
                class_def = Class.forName(types[i]);
            } catch (Exception e) {}
            ArrayList <Class<?>> kol = new ArrayList<>();
            df.set(i,kol);
            this.names.set(i, names[i]);
        }
    }
    public int size() {
        return names.size();
    }

    public ArrayList get(String colname) {
        return df.get(names.indexOf(colname));
    }

    public DataFrame iloc(int i) {
        DataFrame result = new DataFrame(names.get(i), df.get(i).getClass().getCanonicalName());
        result.df.set(0, this.df.get(i));
        return result;
    }

    public DataFrame iloc(int from, int to) {
        int size = to - from + 1;
        String [] types = new String[size];
        for (int i = 0; i < size; i++) {
            types[i] = df.get(i).getClass().getCanonicalName();
        }
        DataFrame result = new DataFrame((String[]) names.subList(from, to).toArray(), types);
        return result;
    }
}
