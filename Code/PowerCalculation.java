public class PowerCalculation {
    // Naive recursive power function
    public static int powerNaive(int x, int n) {
        if (n == 0) return 1; // base case
        return x * powerNaive(x, n - 1); // recursive call
    }

    // Optimized recursive power function
    public static int powerOptimized(int x, int n) {
        if (n == 0) return 1; // base case
        if (n % 2 == 0) {
            int half = powerOptimized(x, n / 2);
            return half * half;
        } else {
            int half = powerOptimized(x, (n - 1) / 2);
            return x * half * half;
        }
    }

    public static void main(String[] args) {
        int x = 2, n = 10;
        System.out.println("Naive Power: " + powerNaive(x, n));
        System.out.println("Optimized Power: " + powerOptimized(x, n));
    }
}


