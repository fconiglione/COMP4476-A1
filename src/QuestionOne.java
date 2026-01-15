import java.util.Scanner;

public class QuestionOne {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ,.";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the key: ");
        String[] input = scanner.nextLine().trim().split("\\s+");
        int[] key = new int[29];
        for (int i = 0; i < 29; i++) {
            key[i] = Integer.parseInt(input[i]);
        }

        System.out.println("\nEnter a paragraph of text: ");
        String plaintext = scanner.nextLine().toUpperCase();

        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);

        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted: " + decryptedText);
    }

    public static String encrypt(String text, int[] key) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = ALPHABET.indexOf(c);
            if (index != -1) {
                int encryptedIndex = key[index];
                sb.append(ALPHABET.charAt(encryptedIndex));
            }
        }
        return sb.toString();
    }

    public static String decrypt(String text, int[] key) {
        int[] inverseKey = new int[29];
        for (int i = 0; i < key.length; i++) {
            inverseKey[key[i]] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = ALPHABET.indexOf(c);
            if (index != -1) {
                int decryptedIndex = inverseKey[index];
                sb.append(ALPHABET.charAt(decryptedIndex));
            }
        }
        return sb.toString();
    }
}