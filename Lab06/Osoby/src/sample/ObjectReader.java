package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectReader {

    protected static Object readFromString(String str, Class class_) {
        Pattern pattern = Pattern.compile("([\\w ]+)/(\\d+)/(\\d{11})");
        if (class_.getName().equals("sample.Person")) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                String fullName = matcher.group(1);
                String phone = matcher.group(2);
                String pesel = matcher.group(3);
                Person person = new Person(fullName, phone, pesel);
                return person;
            }
        }
        return null;
    }

    public static Object readFromInputStream(Class class_) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            String str = bf.readLine();
            Object obj = readFromString(str, class_);
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
