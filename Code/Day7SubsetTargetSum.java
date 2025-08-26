import java.util.*;

/**
 * Subset Sum (print ALL subsets whose sum == target)
 *
 * This file shows TWO approaches side-by-side:
 *  1) YOUR VERSION: Sum is computed at the leaf (when index == n)
 *  2) OPTIMIZED: Carry a runningSum during recursion (no O(n) loop at leaves)
 *
 * Both print the same answers; the optimized one does less work per leaf.
 *
 * Example:
 *   arr = [2,3,5], target = 5
 *   Output subsets: [2, 3], [5]
 */
public class SubsetSumAll {

    public static void main(String[] args) {
        int[] arr = {2, 3, 5};
        int target = 5;

        System.out.println("=== YOUR VERSION: sum-at-leaf (O(n * 2^n)) ===");
        List<Integer> path1 = new ArrayList<>();
        subsetSum_userVersion(arr, path1, 0, target);    // your code, preserved

        System.out.println("\n=== OPTIMIZED: running sum (O(2^n)) ===");
        List<Integer> path2 = new ArrayList<>();
        subsetSum_runningSum(arr, path2, 0, target, 0);  // optimized
    }

    /* **********************************************************************
     * 1) YOUR VERSION (kept very close to what you wrote)
     *    - Generate all subsets by include/exclude
     *    - At the leaf (index == arr.length), compute sum by looping path
     *    - If sum == target -> print it
     *
     * Time:  O(n * 2^n)  (2^n leaves, and each leaf does O(n) sum)
     * Space: O(n)        (recursion depth + path)
     ********************************************************************** */
    public static void subsetSum_userVersion(int[] arr, List<Integer> path, int index, int target) {
        // Base: considered all elements
        if (index == arr.length) {
            // Compute sum at leaf (extra O(n) cost)
            int sum = 0;
            for (int v : path) sum += v;

            if (sum == target) {
                System.out.println(path);
            }
            return; // IMPORTANT: always return at base
        }

        // Choice 1: include arr[index]
        path.add(arr[index]);
        subsetSum_userVersion(arr, path, index + 1, target);

        // Backtrack (undo the choice)
        path.remove(path.size() - 1);

        // Choice 2: exclude arr[index]
        subsetSum_userVersion(arr, path, index + 1, target);
    }

    /* **********************************************************************
     * 2) OPTIMIZED VERSION (carry runningSum)
     *    - Same include/exclude recursion
     *    - Maintain current sum as we go (runningSum)
     *    - At leaf, just compare runningSum == target (O(1))
     *
     * Time:  O(2^n)      (no O(n) loop at leaves)
     * Space: O(n)        (recursion depth + path)
     *
     * Optional pruning idea (not applied here because negatives may exist):
     *   If all numbers are non-negative and runningSum > target, you can stop early.
     ********************************************************************** */
    public static void subsetSum_runningSum(int[] arr, List<Integer> path, int index, int target, int runningSum) {
        // Base: considered all elements
        if (index == arr.length) {
            if (runningSum == target) {
                System.out.println(path);
            }
            return;
        }

        // Choice 1: include arr[index]
        path.add(arr[index]);
        subsetSum_runningSum(arr, path, index + 1, target, runningSum + arr[index]);

        // Backtrack (undo)
        path.remove(path.size() - 1);

        // Choice 2: exclude arr[index]
        subsetSum_runningSum(arr, path, index + 1, target, runningSum);
    }
}
