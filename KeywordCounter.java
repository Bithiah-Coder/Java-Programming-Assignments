import java.io.*;
import java.util.*;

public class Exercise21_03 {
    static final Set<String> keywords = Set.of(
        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
        "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
        "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
        "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
        "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
        "volatile", "while"
    );

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java Exercise21_03 <JavaSourceFile.java>");
            return;
        }

        Scanner input = new Scanner(new File(args[0]));
        StringBuilder code = new StringBuilder();
        while (input.hasNextLine()) code.append(input.nextLine()).append("\n");
        input.close();

        String text = code.toString()
            .replaceAll("//.*", "")                   // Remove line comments
            .replaceAll("/\\*.*?\\*/", "")            // Remove block comments
            .replaceAll("\"(\\\\.|[^\"\\\\])*\"", ""); // Remove string literals

        int count = 0;
        for (String word : text.split("\\W+")) {
            if (keywords.contains(word)) count++;
        }

        System.out.println("The number of keywords in the program is " + count);
    }
}
