/*Key Notes for Interview Prep:

Approach 1 (Backtracking with skipping duplicates):
Sort input first.
Skip duplicate numbers in the same recursion level (if (i > index && arr[i] == arr[i - 1]) continue;).
Efficient, commonly used in interviews.

Approach 2 (HashSet-based):
Use recursion to generate all subsets.
Sort each subset before storing → ensures [2,1] and [1,2] look the same.
Store in HashSet to auto-remove duplicates.
Simpler to implement but less efficient (extra sorting, string conversion). */




import java.util.*;

public class Main {
    public static void main(String[] args) {
        int nums[] = {1, 2, 2};

        System.out.println("✅ Approach 1: Backtracking with Duplicate Skipping");
        Arrays.sort(nums); // Sorting helps to group duplicates together
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(nums, 0, new ArrayList<>(), result);
        System.out.println(result);

        System.out.println("\n✅ Approach 2: Using HashSet to Avoid Duplicates");
        List<String> ans = subsetsWithDup(nums);
        printAns(ans);
    }

    // ------------------------------------------------------------
    // APPROACH 1: Backtracking with Skipping Duplicates
    // ------------------------------------------------------------
    private static void generateSubsets(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
        // Always add the current subset (make a copy!)
        result.add(new ArrayList<>(current));

        // Explore all choices starting from "index"
        for (int i = index; i < arr.length; i++) {
            // ⚡ Key Step: Skip duplicates
            // If current element is same as previous and we are at the same tree level → skip
            if (i > index && arr[i] == arr[i - 1]) continue;

            // Include element
            current.add(arr[i]);
            generateSubsets(arr, i + 1, current, result);

            // Backtrack → remove last added element
            current.remove(current.size() - 1);
        }
    }

    // ------------------------------------------------------------
    // APPROACH 2: Using HashSet to Automatically Handle Duplicates
    // ------------------------------------------------------------
    public static void fun(int[] nums, int index, List<Integer> ds, HashSet<String> res) {
        if (index == nums.length) {
            // Sort each subset before adding → ensures duplicate subsets look identical
            Collections.sort(ds);
            res.add(ds.toString()); // Store as string to maintain uniqueness
            return;
        }

        // Choice 1: Include nums[index]
        ds.add(nums[index]);
        fun(nums, index + 1, ds, res);

        // Choice 2: Exclude nums[index]
        ds.remove(ds.size() - 1);
        fun(nums, index + 1, ds, res);
    }

    public static List<String> subsetsWithDup(int[] nums) {
        List<String> ans = new ArrayList<>();
        HashSet<String> res = new HashSet<>();
        List<Integer> ds = new ArrayList<>();
        fun(nums, 0, ds, res);

        // Convert HashSet back to List
        ans.addAll(res);
        return ans;
    }

    static void printAns(List<String> ans) {
        System.out.println("The unique subsets are: ");
        System.out.println(ans.toString().replace(",", " "));
    }
}
