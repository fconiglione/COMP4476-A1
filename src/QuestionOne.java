import java.util.Scanner;

public class QuestionOne {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ,.";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Enter the key for the program
        System.out.println("Enter the key: ");
        String[] input = scanner.nextLine().trim().split("\\s+");
        int[] key = new int[29];
        for (int i = 0; i < 29; i++) {
            key[i] = Integer.parseInt(input[i]);
        }

        // Request input text of at least 30 words
        System.out.println("\nEnter a paragraph of text (at least 30 words): ");
        String plaintext = scanner.nextLine().toUpperCase();

        // Call encryption method and display output
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);

        // Call decryption method and display output
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted: " + decryptedText);
    }

    public static String encrypt(String text, int[] key) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            // Find the original index of c
            int index = ALPHABET.indexOf(c);
            if (index != -1) {
                // Replace the original index with the corresponding key index
                int encryptedIndex = key[index];
                // Add the alphabet at the encrypted index to the sb
                sb.append(ALPHABET.charAt(encryptedIndex));
            }
        }
        return sb.toString();
    }

    public static String decrypt(String text, int[] key) {
        // Create the inverse key for decryption
        int[] inverseKey = new int[29];
        for (int i = 0; i < key.length; i++) {
            inverseKey[key[i]] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            // Find the encrypted index
            int index = ALPHABET.indexOf(c);
            if (index != -1) {
                // Identify the original string
                int decryptedIndex = inverseKey[index];
                sb.append(ALPHABET.charAt(decryptedIndex));
            }
        }
        return sb.toString();
    }
}