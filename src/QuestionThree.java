import java.util.*;

public class QuestionThree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Enter the known plaintext
        System.out.println("Enter the known Plaintext:");
        String plaintext = scanner.nextLine();

        // Enter the ciphertext
        System.out.println("Enter the corresponding Ciphertext:");
        String ciphertext = scanner.nextLine();

        // Call the findkey() function
        findKey(plaintext, ciphertext);
    }

    public static void findKey(String p, String c) {
        // Remove any whitespace
        p = p.replaceAll("\\s", "");
        c = c.replaceAll("\\s", "");

        int n = p.length();

        // Try every possible block size (m)
        for (int m = 1; m <= 30 && m <= n; m++) {

            int[] key = new int[m];
            Arrays.fill(key, -1);
            boolean possible = true;

            // For every position 'i' in the plaintext block, try to find a matching column 'j' in the ciphertext block
            for (int i = 0; i < m; i++) {
                boolean colFound = false;

                for (int j = 0; j < m; j++) {
                    // See if this 'j' is used by another 'i'
                    boolean used = false;
                    for (int k = 0; k < i; k++) {
                        if (key[k] == j) {
                            used = true;
                            break;
                        }
                    }
                    if (used) continue;

                    // Ensure mapping works for all blocks
                    boolean works = true;
                    for (int block = 0; block < n / m; block++) {
                        int pIndex = (block * m) + i;
                        int cIndex = (block * m) + j;

                        if (p.charAt(pIndex) != c.charAt(cIndex)) {
                            works = false;
                            break;
                        }
                    }

                    // If matches, add to key
                    if (works) {
                        key[i] = j;
                        colFound = true;
                        break;
                    }
                }

                // If no match, then block is incorrect
                if (!colFound) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                System.out.println("\n--- Attack Successful ---");
                System.out.println("Value of 'm': " + m);
                System.out.print("Permutation Key: ");
                for (int val : key) System.out.print(val + " ");
                System.out.println();
                return; // Stop after finding the first valid key
            }
        }
        System.out.println("Failed. Try again.");
    }
}