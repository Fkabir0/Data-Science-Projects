import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class PalindromeChecker {
    public static void main(String[] args) {
        // Ask the user for the file path
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file path to input.txt: ");
        String inputFile = scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines

                // Process each input line
                boolean isPalindrome = isPalindrome(line.trim());
                System.out.println(line.trim() + (isPalindrome ? " Yes" : " No"));
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading the input file: " + e.getMessage());
        } finally {
            scanner.close(); // Close the Scanner object
        }
    }

    public static boolean isPalindrome(String str) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();

        // Populate stack and queue with characters from the input string
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) { // Ignore non-letter characters
                stack.push(Character.toLowerCase(c));
                queue.add(Character.toLowerCase(c));
            }
        }

        // Compare characters from stack and queue
        while (!stack.isEmpty() && !queue.isEmpty()) {
            if (!stack.pop().equals(queue.remove())) {
                return false; // If characters don't match, it's not a palindrome
            }
        }

        return true; // If all characters match, it's a palindrome
    }
}
