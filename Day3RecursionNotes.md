📘 Day 3 – Recursion & Fibonacci Optimization Notes

🔹 What I Learned Today
    Recursion Deep Dive
    A function calling itself.
    Base case is mandatory to stop infinite calls.
    Helps break big problems into smaller subproblems.

Different Types of Recursion

    **Direct Recursion** → Function calls itself directly.
    Example: factorial(n)
    **Indirect Recursion** → Function A calls B, and B calls A.
    Tail Recursion → Recursive call is the last operation in the function.
    Example: Tail-optimized factorial.
    **Non-Tail Recursion** → Work is done after recursive call.
    Example: Fibonacci, Tree Traversals.
    **Mutual Recursion** → Two or more functions call each other.
    **Linear Recursion** → One recursive call at each step.
    **Tree Recursion** → Function makes multiple recursive calls (e.g., Fibonacci).

✅ Most Important Types for Coding Problems:
    Non-Tail Recursion → Fibonacci, Tree traversals, Divide & Conquer problems.
    Tail Recursion → Optimizing space (can be converted to loops).
    Tree Recursion → Problems like generating subsets, permutations, backtracking.

🔹 Fibonacci Example (Basic Recursion)
        public static int fib(int n) {
            if (n == 0 || n == 1)
                return n;
            return fib(n - 1) + fib(n - 2);
        }
        Time Complexity: O(2^n) → Exponential (very slow).
        Space Complexity: O(n) (recursive stack).

🔹 Fibonacci with Memoization (Top-Down DP)
        public static int fib(int n, int[] dp) {
            if (n == 0 || n == 1) return n;

            if (dp[n] != 0) return dp[n];  // Re-use stored result

            dp[n] = fib(n - 1, dp) + fib(n - 2, dp);
            return dp[n];
        }

        Time Complexity: O(n)
        Space Complexity: O(n) (for dp + recursion stack).

🔹 Fibonacci with Tabulation (Bottom-Up DP)
        public static int fib(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;

            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
        Time Complexity: O(n)
        Space Complexity: O(n)

🔹 Fibonacci Optimized (Space Efficient)
        public static int fib(int n) {
            if (n == 0) return 0;
            if (n == 1) return 1;

            int prev1 = 1, prev2 = 0, curr = 0;

            for (int i = 2; i <= n; i++) {
                curr = prev1 + prev2;
                prev2 = prev1;
                prev1 = curr;
            }
            return curr;
        }
        Time Complexity: O(n)
        Space Complexity: O(1) ✅ (best optimized)

🔹 Key Takeaways
    Naive recursion is slow → avoid for large n.
    Memoization improves efficiency.
    Tabulation is iterative & avoids recursion overhead.
    Space optimized solution is best for Fibonacci.
    Tree recursion (like in Fibonacci) is common in problems involving combinations, subsets, and backtracking.
    Tail recursion is useful when you want space efficiency, as compilers can optimize it.