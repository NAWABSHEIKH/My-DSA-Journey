import java.util.*;

public class SPC_AllInOne {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        String str = "abc";

        System.out.println("=== SUBSETS (backtracking include/exclude) ===");
        subsetsBacktrack(arr);

        System.out.println("\n=== SUBSETS (bitmask) ===");
        subsetsBitmask(arr);

        System.out.println("\n=== COMBINATIONS k=2 ===");
        combinationsK(arr, 2);

        System.out.println("\n=== PERMUTATIONS (used[] approach, String) ===");
        permutationsString(str);

        System.out.println("\n=== PERMUTATIONS (swap approach, int[]) ===");
        permutationsSwap(arr.clone()); // use clone so original isn't modified
    }

    /* ============================= SUBSETS ============================= */

    // 1) Subsets via backtracking (include/exclude)
    public static void subsetsBacktrack(int[] nums) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfsSubsets(nums, 0, path, res);
        printListOfLists(res);
    }

    private static void dfsSubsets(int[] nums, int idx, List<Integer> path, List<List<Integer>> res) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // choose
        path.add(nums[idx]);
        dfsSubsets(nums, idx + 1, path, res);
        path.remove(path.size() - 1);
        // skip
        dfsSubsets(nums, idx + 1, path, res);
    }

    // 2) Subsets via bitmask (iterative)
    public static void subsetsBitmask(int[] nums) {
        int n = nums.length;
        int total = 1 << n;
        for (int mask = 0; mask < total; mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) subset.add(nums[i]);
            }
            System.out.println(subset);
        }
    }

    /* =========================== COMBINATIONS ========================== */

    // combinations: choose k elements (order doesn't matter)
    public static void combinationsK(int[] nums, int k) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfsComb(nums, 0, k, path, res);
        printListOfLists(res);
    }

    private static void dfsComb(int[] nums, int start, int k, List<Integer> path, List<List<Integer>> res) {
        // base: got k elements
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // pruning: if remaining elements < needed, we can return early (optional)
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);                 // choose
            dfsComb(nums, i + 1, k, path, res); // move forward (avoid duplicates)
            path.remove(path.size() - 1);      // un-choose
        }
    }

    /* =========================== PERMUTATIONS ========================== */

    // A) Permutations of a String via used[]
    public static void permutationsString(String s) {
        boolean[] used = new boolean[s.length()];
        StringBuilder path = new StringBuilder();
        List<String> res = new ArrayList<>();
        dfsPermString(s, used, path, res);
        for (String p : res) System.out.println(p);
    }

    private static void dfsPermString(String s, boolean[] used, StringBuilder path, List<String> res) {
        if (path.length() == s.length()) {
            res.add(path.toString());
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!used[i]) {
                used[i] = true;
                path.append(s.charAt(i));
                dfsPermString(s, used, path, res);
                path.deleteCharAt(path.length() - 1);
                used[i] = false;
            }
        }
    }

    // B) Permutations of int[] via swap
    public static void permutationsSwap(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfsPermSwap(nums, 0, res);
        printListOfLists(res);
    }

    private static void dfsPermSwap(int[] nums, int idx, List<List<Integer>> res) {
        if (idx == nums.length) {
            List<Integer> perm = new ArrayList<>();
            for (int v : nums) perm.add(v);
            res.add(perm);
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            swap(nums, idx, i);
            dfsPermSwap(nums, idx + 1, res);
            swap(nums, idx, i); // backtrack
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    /* ============================== UTILS ============================== */

    private static void printListOfLists(List<List<Integer>> lol) {
        for (List<Integer> l : lol) System.out.println(l);
    }
}
