import java.util.Scanner;

// Custom exception to handle invalid binary strings
class BinaryFormatException extends Exception {
    public BinaryFormatException(String message) {
        super(message);
    }
}

// Class with method to convert binary string to decimal
public class BinaryConversion {

    // Method to convert binary string to decimal
    public static int bin2Dec(String binaryString) throws BinaryFormatException {
        // Check if the string contains only '0' and '1'
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) != '0' && binaryString.charAt(i) != '1') {
                // If it's not a valid binary digit, throw custom exception
                throw new BinaryFormatException("Not a binary number");
            }
        }

        // Convert the binary string to a decimal number
        return Integer.parseInt(binaryString, 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for a binary number input
        System.out.print("Enter a binary number: ");
        String binaryString = scanner.nextLine();

        try {
            // Call the bin2Dec method to convert binary to decimal
            int decimal = bin2Dec(binaryString);
            System.out.println("Decimal equivalent: " + decimal);
        } catch (BinaryFormatException e) {
            // Handle invalid binary string and show error message
            System.out.println(e.getMessage());
        }
    }
}
