import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class CandySymbolChecker {

    public static void main(String[] gumdrops) {
        if (gumdrops.length != 1) {
            System.out.println("🍬 Please provide the Java source file as a command-line argument.");
            return;
        }

        String candyWrapper = gumdrops[0];
        File candyFile = new File(candyWrapper);

        try (Scanner candyScanner = new Scanner(candyFile)) {
            Stack<Character> candyJar = new Stack<>();
            int lineNumber = 0;
            boolean sweetSuccess = true;

            while (candyScanner.hasNextLine()) {
                String licoriceLine = candyScanner.nextLine();
                lineNumber++;

                for (int i = 0; i < licoriceLine.length(); i++) {
                    char candy = licoriceLine.charAt(i);

                    if (candy == '(' || candy == '{' || candy == '[') {
                        candyJar.push(candy);
                    } else if (candy == ')' || candy == '}' || candy == ']') {
                        if (candyJar.isEmpty()) {
                            System.out.println("🍭 Mismatched closing " + candy + " at line " + lineNumber);
                            sweetSuccess = false;
                            break;
                        }

                        char topCandy = candyJar.pop();

                        if (!isMatchingPair(topCandy, candy)) {
                            System.out.println("🍬 Mismatched symbols: found '" + candy + "' but expected match for '" + topCandy + "' at line " + lineNumber);
                            sweetSuccess = false;
                            break;
                        }
                    }
                }

                if (!sweetSuccess) {
                    break;
                }
            }

            if (sweetSuccess && candyJar.isEmpty()) {
                System.out.println("🍫 Sweet! All your grouping symbols are perfectly matched.");
            } else if (!candyJar.isEmpty()) {
                System.out.println("🍩 Oops! There are unmatched opening candies (grouping symbols).");
            }

        } catch (FileNotFoundException e) {
            System.out.println("🍬 Error: Could not find the file " + candyWrapper);
        }
    }

    private static boolean isMatchingPair(char openCandy, char closeCandy) {
        return (openCandy == '(' && closeCandy == ')') ||
               (openCandy == '{' && closeCandy == '}') ||
               (openCandy == '[' && closeCandy == ']');
    }
}
