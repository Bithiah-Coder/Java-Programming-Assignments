import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class GroupingSymbolChecker {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java GroupingSymbolChecker <JavaSourceFile.java>");
            return;
        }

        String filename = args[0];
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            Stack<Character> stack = new Stack<>();
            int lineNumber = 0;
            boolean errorFound = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber++;

                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);

                    if (ch == '(' || ch == '{' || ch == '[') {
                        stack.push(ch);
                    } else if (ch == ')' || ch == '}' || ch == ']') {
                        if (stack.isEmpty()) {
                            System.out.println("Unmatched closing '" + ch + "' at line " + lineNumber);
                            errorFound = true;
                            break;
                        }

                        char lastOpen = stack.pop();
                        if (!isMatchingPair(lastOpen, ch)) {
                            System.out.println("Mismatched '" + lastOpen + "' and '" + ch + "' at line " + lineNumber);
                            errorFound = true;
                            break;
                        }
                    }
                }

                if (errorFound) {
                    break;
                }
            }

            if (!errorFound) {
                if (stack.isEmpty()) {
                    System.out.println("All grouping symbols are correctly matched.");
                } else {
                    System.out.println("Unmatched opening symbol(s) remain at end of file.");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}
