package lab4;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.regex.*;

public class MicroDVD {

    static String delay(String in, int delay, int fps) throws WrongCharacter, EndLessThanStart {
        int move = delay * fps / 1000;
        Pattern pattern = Pattern.compile("\\{\\w*\\}\\{\\w*\\}");
        Pattern pattern1 = Pattern.compile("\\{\\d*\\}\\{\\d*\\}");
        Pattern pattern2 = Pattern.compile("\\d*");
        Pattern pattern_rest = Pattern.compile("\\}[^\\{]+");
        Matcher matcher = pattern.matcher(in);
        Matcher matcher_rest = pattern_rest.matcher(in);
        String partOfText = new String();
        int[] values = new int[2];
        String currentLineResult = new String();
        int i = 0;
        while (matcher.find()) {
            partOfText = matcher.group();
            Matcher matcher1 = pattern1.matcher(partOfText);
            if (!matcher1.matches()) {
                throw new WrongCharacter(partOfText);
            }
            Matcher matcher2 = pattern2.matcher(partOfText);
            while (matcher2.find()) {
                try {
                    values[i] = Integer.parseInt(matcher2.group());
                    ++i;
                } catch (NumberFormatException e) {
                }
            }
            i = 0;
            if (values[0] > values[1]) {
                throw new EndLessThanStart(partOfText);
            }
            values[0] += move;
            values[1] += move;

            System.out.println(partOfText);
        }
        String out = new String();
        if (matcher_rest.find()) {
            out = "{" + values[0] + "}" + "{" + values[1] + matcher_rest.group();
//                        out.replaceAll(partOfText, "{" + values[0] + "}" +
//                                "{" + values[1] + "}");
        }
        return out;
    }
}
