Day 1 – Time Complexity, Space Complexity & Recursion Basics

📌 1. Why Complexity Matters?
Data grows fast → brute force may fail.
Complexity helps measure time & memory growth with input size n.

📌 2. Big-O, Big-Ω, Big-Θ
Big-O (O) → Worst case (Upper bound).
Big-Ω (Ω) → Best case (Lower bound).
Big-Θ (Θ) → Average case (Tight bound).

Common Complexities:
    Complexity	Example
    O(1)	Accessing array element
    O(log n)	Binary Search
    O(n)	Linear Search
    O(n log n)	Merge Sort
    O(n²)	Nested loops
    O(2^n)	Recursive Fibonacci
    O(n!)	Traveling Salesman (brute force)

📌 3. Iterative vs Recursive Factorial
    Iterative (Efficient)
    public class FactorialIterative {
        public static void main(String[] args) {
            int n = 5;
            int fact = 1;
            for(int i=1; i<=n; i++){
                fact = fact * i;
            }
            System.out.println("Factorial: " + fact);
        }
    }
        Time: O(n)
        Space: O(1) ✅

Recursive
    public class FactorialRecursive {
        public static int fact(int n){
            if(n==1) return 1;   // base case
            return n * fact(n-1);
        }
        
        public static void main(String[] args){
            int n = 5;
            System.out.println("Factorial: " + fact(n));
        }
    }

        Time: O(n)
        Space: O(n) (stack frames) ❌

📌 4. Recursion Examples

    (a) Sum of First N Numbers
    public class FirstNSum {
        public static int firstNSum(int n){
            if(n==0) return 0;  // base case
            return n + firstNSum(n-1);
        }

        public static void main(String[] args){
            System.out.println(firstNSum(10)); // Output: 55
        }
    }

        Time: O(n)
        Space: O(n)

    (b) Fibonacci (Naive)
    public class FibonacciNaive {
        public static int fib(int n){
            if(n==0 || n==1) return n;
            return fib(n-1) + fib(n-2);
        }

        public static void main(String[] args){
            System.out.println(fib(5)); // Output: 5
        }
    }

    Time: O(2^n) ❌ (exponential)
    Space: O(n)

Recursion Tree for fib(5)

                        fib(5)
                        /     \
                    fib(4)    fib(3)
                    /    \     /    \
                fib(3)  fib(2) fib(2) fib(1)
                /   \   /   \
            fib(2) fib(1) fib(1) fib(0)
            /   \
        fib(1) fib(0)
👉 Notice repeated calls: fib(3), fib(2) appear multiple times.

(c) Fibonacci with Memoization (Optimized DP)
        public class FibonacciMemo {
            static int[] dp = new int[100]; // adjust size as needed
            
            public static int fib(int n){
                if(n==0 || n==1) return n;
                if(dp[n] != 0) return dp[n]; // reuse stored value
                dp[n] = fib(n-1) + fib(n-2);
                return dp[n];
            }

            public static void main(String[] args){
                System.out.println(fib(10)); // Output: 55
            }
        }
        Time: O(n) ✅
        Space: O(n)

📌 5. Key Takeaways (Day 1)
1.Always analyze both time & space complexity.
2.Iterative factorial is better than recursive due to lower space.
3.Naive Fibonacci recursion is exponential → avoid in interviews.
4.Use DP / memoization to optimize recursive problems.
5.Recursion is powerful for trees, backtracking, divide & conquer.