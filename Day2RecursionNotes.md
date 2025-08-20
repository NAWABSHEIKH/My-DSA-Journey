Power Calculation (Exponential Multiplication)
❌ Brute Force (O(n))
Compute a^b by multiplying a exactly b times.
Example: 2^5 = 2 * 2 * 2 * 2 * 2

            public class PowerBruteForce {
                public static int power(int a, int b) {
                    if (b == 0) return 1;
                    return a * power(a, b - 1);
                }

                public static void main(String[] args) {
                    System.out.println("2^5 = " + power(2, 5)); // Output: 32
                }
            }

✅ Optimized Approach: Exponentiation by Squaring (O(log n))
Idea:
If exponent b is even → a^b = (a^(b/2)) * (a^(b/2))
If exponent b is odd → a^b = a * (a^(b/2)) * (a^(b/2))
This reduces the problem size by half each step → O(log n).
Example Walkthrough: Compute 2^8

            2^8  
            = (2^4) * (2^4)  
            = ((2^2) * (2^2)) * ((2^2) * (2^2))  
            = (((2^1)*(2^1)) * ((2^1)*(2^1))) * (((2^1)*(2^1)) * ((2^1)*(2^1)))

We only do 3 recursive calls instead of 8 multiplications.

Code:

        public class PowerOptimized {
            public static int power(int a, int b) {
                if (b == 0) return 1;
                int half = power(a, b / 2);

                if (b % 2 == 0) {
                    return half * half;          // even power
                } else {
                    return a * half * half;      // odd power
                }
            }

            public static void main(String[] args) {
                System.out.println("2^8 = " + power(2, 8)); // Output: 256
            }
        }

📝 Summary
Factorial: Simple recursion.

Fibonacci:
Brute Force → O(2^n) (bad for large n).
Optimized (Memoization/DP) → O(n).
Power Calculation (a^b):
Brute Force → O(n)
Optimized (Exponentiation by Squaring) → O(log n).
