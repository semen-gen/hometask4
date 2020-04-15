package local;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    static String testText = "      Знаков препинания: Hello ,;.?! может быть много?!         ";

    public static void main(String[] args) {
        searchSymbol();
        System.out.println();

        countWords();
        System.out.println();

        lastLetters();
        System.out.println();

        summariseLetters();
        System.out.println();
    }

    static void searchSymbol() {
        Pattern p = Pattern.compile("[,;.?!:]");
        Matcher matcher = p.matcher(testText);
        int i = 0;
        while (matcher.find()) {
            i++;
            System.out.print(matcher.group() + " ");
        }
        System.out.println();
        System.out.println("Общее количество найденных знаков препинания = " + i);
    }

    static void countWords() {
        // Работают три варианта шаблонов
//        Pattern p = Pattern.compile("\\w+\\b", Pattern.UNICODE_CHARACTER_CLASS);
//        Pattern p = Pattern.compile("(?U)\\w+\\b");
        Pattern p = Pattern.compile("\\p{L}+\\b");
        Matcher matcher = p.matcher(testText);

        int i = 0;
        while (matcher.find()) {
            i++;
            System.out.print(matcher.group() + " ");
        }

        System.out.println();
        System.out.println("Общее количество найденных слов = " + i);
    }

    static void lastLetters() {
        Pattern p = Pattern.compile("\\p{L}\\b");
        Matcher matcher = p.matcher(testText);

        while (matcher.find()) {
            System.out.print(matcher.group());
        }
        System.out.println();
    }

    static void summariseLetters() {
        long beforeSimple, afterSimple, beforeBuilder, afterBuilder;
        int iteration = 30000;
        String[] arrayStrings = new String[]{"Два ", "Один "};
        String simple = "";
        StringBuilder builder = new StringBuilder();

        beforeSimple = Calendar.getInstance().getTimeInMillis();
        for (int i = 1; i <= iteration; i++) {
            simple += arrayStrings[i % arrayStrings.length];
        }
        afterSimple = Calendar.getInstance().getTimeInMillis();
        System.out.println(simple);
        System.out.println(afterSimple - beforeSimple);

        beforeBuilder = Calendar.getInstance().getTimeInMillis();
        for (int i = 1; i <= iteration; i++) {
            builder.append(arrayStrings[i % arrayStrings.length]);
        }
        afterBuilder = Calendar.getInstance().getTimeInMillis();
        System.out.println(builder);
        System.out.println(afterBuilder - beforeBuilder);
    }
}
