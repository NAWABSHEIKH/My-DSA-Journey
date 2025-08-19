import java.util.HashMap;

public class FibonacciComparison {

    // --------------------------
    // 1. Naive Recursive Fibonacci (Worst O(2^n))
    // --------------------------
    public static int fibRecursive(int n) {
        if (n <= 1) {
            return n; // base case: fib(0)=0, fib(1)=1
        }
        // recursive calls for fib(n-1) and fib(n-2)
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    // --------------------------
    // 2. Optimized Fibonacci with Memoization (Best O(n))
    // --------------------------
    private static HashMap<Integer, Integer> memo = new HashMap<>();

    public static int fibMemoization(int n) {
        if (n <= 1) {
            return n; // base case
        }
        // check if value already computed
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        // store result in memo before returning
        int result = fibMemoization(n - 1) + fibMemoization(n - 2);
        memo.put(n, result);
        return result;
    }

    // --------------------------
    // 3. Iterative Fibonacci (Also O(n) but without recursion)
    // --------------------------
    public static int fibIterative(int n) {
        if (n <= 1) return n;
        int prev2 = 0, prev1 = 1;
        int current = 0;
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

    // --------------------------
    // Main method for testing
    // --------------------------
    public static void main(String[] args) {
        int n = 10;

        System.out.println("Naive Recursive Fibonacci of " + n + ": " + fibRecursive(n));
        System.out.println("Memoized Fibonacci of " + n + ": " + fibMemoization(n));
        System.out.println("Iterative Fibonacci of " + n + ": " + fibIterative(n));
    }
}
