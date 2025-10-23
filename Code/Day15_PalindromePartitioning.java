import java.util.*;

public class PalindromePartitioning {

    // Main method to test
    public static void main(String[] args) {
        String s = "aabb";
        List<List<String>> result = partition(s);

        // Print all palindrome partitions
        System.out.println("All possible palindrome partitions:");
        for (List<String> list : result) {
            System.out.println(list);
        }
    }

    // This is the main function that will return the final answer
    static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>(); // To store all valid partitions
        List<String> current = new ArrayList<>();      // To store the current path during recursion

        // Start recursion from index 0
        backtrack(0, s, current, result);
        return result;
    }

    // Recursive function that tries to partition the string from 'start' index
    static void backtrack(int start, String s, List<String> current, List<List<String>> result) {

        // BASE CONDITION 🧠
        // If 'start' has reached the end of string,
        // it means we have considered all characters,
        // and 'current' contains one valid partition.
        if (start == s.length()) {
            result.add(new ArrayList<>(current)); // Add a copy of current list to result
            return; // End recursion for this path
        }

        // LOOP 🌀
        // Try making partitions by expanding substring step by step
        for (int i = start; i < s.length(); i++) {

            // Extract substring from current start to i (inclusive)
            String part = s.substring(start, i + 1);

            // Check if this substring is palindrome
            if (isPalindrome(s, start, i)) {

                // If palindrome, we can safely include it
                current.add(part);

                // RECURSION CALL 🔁
                // Move ahead to find partitions for the remaining string (i+1)
                backtrack(i + 1, s, current, result);

                // BACKTRACK 🧹
                // Remove the last added substring to try a new one
                current.remove(current.size() - 1);
            }

            // If not palindrome, simply skip to next 'i'
        }
    }

    // Helper function to check if substring s[left...right] is a palindrome
    static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            // If mismatch found → not palindrome
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            // Move inward from both ends
            left++;
            right--;
        }
        // If loop ends → all characters matched → palindrome ✅
        return true;
    }
}
