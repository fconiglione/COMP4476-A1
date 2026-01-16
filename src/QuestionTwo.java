import java.util.Scanner;

public class QuestionTwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Enter the permutation size
        System.out.print("Enter the permutation size (m): ");
        int m = scanner.nextInt();

        // Enter the permutation key
        System.out.println("Enter the permutation key (numbers 0 to " + (m-1) + " separated by spaces):");
        int[] key = new int[m];
        // Ensure permutation key remains smaller than permutation size
        for (int i = 0; i < m; i++) {
            int val = scanner.nextInt();
            if (val >= m || val < 0) {
                System.out.println("Error: Number " + val + " is out of bounds for size " + m);
                return;
            }
            key[i] = val;
        }
        scanner.nextLine();

        // CLI Requests

        System.out.println("\nEnter a paragraph (at least 30 words):");
        String plaintext = scanner.nextLine();

        String ciphertext = encrypt(plaintext, key, m);
        System.out.println("\nCiphertext: " + ciphertext);

        String decryptedText = decrypt(ciphertext, key, m);
        System.out.println("Decrypted: " + decryptedText);
    }

    public static String encrypt(String text, int[] key, int m) {
        StringBuilder paddedText = new StringBuilder(text);
        // Add padding to ensure text fits within the permutation size (m)
        while (paddedText.length() % m != 0) {
            paddedText.append(" ");
        }

        StringBuilder result = new StringBuilder();

        // Develop the new encrypted block
        for (int i = 0; i < paddedText.length(); i += m) {
            char[] block = new char[m];
            for (int j = 0; j < m; j++) {
                block[key[j]] = paddedText.charAt(i + j);
            }
            result.append(new String(block));
        }
        return result.toString();
    }

    public static String decrypt(String text, int[] key, int m) {
        // Create the inverse key for decryption
        int[] inverseKey = new int[m];
        for (int i = 0; i < m; i++) {
            inverseKey[key[i]] = i;
        }

        StringBuilder result = new StringBuilder();

        // Develop the decrypted block
        for (int i = 0; i < text.length(); i += m) {
            char[] block = new char[m];
            for (int j = 0; j < m; j++) {
                block[inverseKey[j]] = text.charAt(i + j);
            }
            result.append(new String(block));
        }
        return result.toString().trim();
    }
}