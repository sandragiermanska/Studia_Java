package sample;

public class ObjectWriter {

    protected static String writeToString(Object obj) {
        String result = new String();
        if (obj instanceof Person) {
            result = ((Person) obj).getFullName() +"/"+ ((Person) obj).getPhone()
                    +"/"+ ((Person) obj).getPesel();
        }
        return result;
    }

    public static void writeToOutputStream(Object obj) {
        System.out.println(writeToString(obj));
    }

}
