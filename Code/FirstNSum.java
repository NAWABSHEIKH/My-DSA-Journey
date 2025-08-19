// Problem: Find sum of first N natural numbers using recursion
public class FirstNSum {
    public static void main(String[] args) {
        int ans = firstNSum(10);
        System.out.println("Sum of first 10 numbers: " + ans);
    }

    // Recursive function to calculate sum of first n numbers
    public static int firstNSum(int n) {
        // Base case
        if (n == 0) 
            return 0;

        // Recursive case: n + sum of previous numbers
        return n + firstNSum(n - 1);
    }
}
