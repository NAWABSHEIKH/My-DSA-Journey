/*    Summary of complexities:
        Naive Recursion → O(2^n) (worst, exponential)
        Memoization (Top-Down DP) → O(n) time, O(n) space.
        Tabulation (Bottom-Up DP) → O(n) time, O(n) space.
        Space Optimized Iterative → O(n) time, O(1) space.
        Matrix Exponentiation → O(log n) time, O(1) space.
        Formula Method → O(1) time, but floating-point issues for large n.    */

public class FibonacciExamples {

    // 1️⃣ Naive Recursion (Exponential Time: O(2^n))
    // Very inefficient for large n
    public static int fibRecursive(int n) {
        if (n <= 1) return n;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    // 2️⃣ Recursion with Memoization (Top-Down DP)
    // Time Complexity: O(n), Space Complexity: O(n)
    public static int fibMemo(int n, int[] dp) {
        if (n <= 1) return n;
        if (dp[n] != -1) return dp[n];  // already computed
        return dp[n] = fibMemo(n - 1, dp) + fibMemo(n - 2, dp);
    }

    // 3️⃣ Iterative Tabulation (Bottom-Up DP)
    // Time Complexity: O(n), Space Complexity: O(n)
    public static int fibTabulation(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 4️⃣ Space Optimized Iterative (Only store last two values)
    // Time Complexity: O(n), Space Complexity: O(1)
    public static int fibSpaceOptimized(int n) {
        if (n <= 1) return n;
        int prev2 = 0, prev1 = 1;
        for (int i = 2; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    // 5️⃣ Matrix Exponentiation
    // Time Complexity: O(log n), Space Complexity: O(1)
    public static int fibMatrix(int n) {
        if (n <= 1) return n;
        int[][] F = {{1, 1}, {1, 0}};
        power(F, n - 1);
        return F[0][0];
    }

    private static void multiply(int[][] F, int[][] M) {
        int x = F[0][0] * M[0][0] + F[0][1] * M[1][0];
        int y = F[0][0] * M[0][1] + F[0][1] * M[1][1];
        int z = F[1][0] * M[0][0] + F[1][1] * M[1][0];
        int w = F[1][0] * M[0][1] + F[1][1] * M[1][1];

        F[0][0] = x; F[0][1] = y;
        F[1][0] = z; F[1][1] = w;
    }

    private static void power(int[][] F, int n) {
        if (n == 0 || n == 1) return;
        int[][] M = {{1, 1}, {1, 0}};
        power(F, n / 2);
        multiply(F, F);
        if (n % 2 != 0) multiply(F, M);
    }

    // 6️⃣ Formula Method (Binet’s Formula)
    // Time Complexity: O(1), but floating point errors may occur for large n
    public static int fibFormula(int n) {
        double phi = (1 + Math.sqrt(5)) / 2;
        return (int) Math.round(Math.pow(phi, n) / Math.sqrt(5));
    }

    // 🔹 Main method for testing all approaches
    public static void main(String[] args) {
        int n = 10;

        System.out.println("Naive Recursion: " + fibRecursive(n));
        
        int[] dp = new int[n + 1];
        java.util.Arrays.fill(dp, -1);
        System.out.println("Memoization: " + fibMemo(n, dp));
        
        System.out.println("Tabulation: " + fibTabulation(n));
        System.out.println("Space Optimized: " + fibSpaceOptimized(n));
        System.out.println("Matrix Exponentiation: " + fibMatrix(n));
        System.out.println("Formula Method: " + fibFormula(n));
    }
}
