/*
        Start []
        - Pick 1 → [1]
            - Pick 2 → [1,2]
                - Pick 3 → [1,2,3] ✅
            - Pick 3 → [1,3]
                - Pick 2 → [1,3,2] ✅
        - Pick 2 → [2]
            - Pick 1 → [2,1]
                - Pick 3 → [2,1,3] ✅
            - Pick 3 → [2,3]
                - Pick 1 → [2,3,1] ✅
        - Pick 3 → [3]
            - Pick 1 → [3,1]
                - Pick 2 → [3,1,2] ✅
            - Pick 2 → [3,2]
                - Pick 1 → [3,2,1] ✅


   Step 1: Initial Setup

Start with an empty path (our current building permutation).
Keep a used[] array to track which elements are already included.

So initially:
path = []
used = [false, false, false]

🔹 Step 2: First Level of Recursion
We loop over all elements {1, 2, 3}.
At each step, if not used, we choose it and go deeper.

👉 Choose 1
path = [1]
used = [true, false, false]

Now recurse to next level.
🔹 Step 3: Second Level
Choices left: {2, 3}
Pick 2
path = [1, 2]
used = [true, true, false]
Pick 3 next level
path = [1, 2, 3]
used = [true, true, true]

Now path.length == arr.length, so print → [1,2,3].

🔹 Step 4: Backtrack
Now we go back one step:
Remove last element (3) → path = [1, 2]
Mark 3 as unused → used = [true, true, false]

Try next choice:
Already tried 3, so return.
Go back again:
Remove 2 → path = [1]
Mark 2 unused → used = [true, false, false]
Now try next element after 2:
👉 Pick 3 → [1, 3]
Continue same process → we’ll get [1,3,2].
🔹 Step 5: Backtracking Visualization Tree
                          []
                 /        |       \
                [1]      [2]      [3]
            /   \     /  \     /   \
        [1,2] [1,3] [2,1] [2,3] [3,1] [3,2]
            |       |     |     |     |     |
        [1,2,3] [1,3,2] [2,1,3] [2,3,1] [3,1,2] [3,2,1]


Each time we reach 3 elements → we print.
✅ So the difference from subset:
In subsets, at each index you decide → take it or skip it.
In permutation, at each step you try all unused options.
That’s why permutations explore more possibilities (n! results instead of 2^n).             
 */


import java.util.*;

public class Day5Permutations {

    public static void main(String[] args) {
        String str = "abc";
        int arr[] = {1, 2, 3};
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

        System.out.println("Permutations of String:");
        findPermutation(str, "", new boolean[str.length()]);

        System.out.println("\nPermutations of Array:");
        findPermutation(arr, new int[arr.length], new boolean[arr.length], 0);

        System.out.println("\nPermutations of ArrayList:");
        findPermutation(list, new ArrayList<>(), new boolean[list.size()]);
    }

    // 🔹 1. Permutation for String
    public static void findPermutation(String str, String ans, boolean[] used) {
        if (ans.length() == str.length()) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!used[i]) {
                used[i] = true;
                findPermutation(str, ans + str.charAt(i), used);
                used[i] = false; // backtrack
            }
        }
    }

    // 🔹 2. Permutation for Array
    public static void findPermutation(int[] arr, int[] ans, boolean[] used, int index) {
        if (index == arr.length) {
            for (int v : ans) System.out.print(v);
            System.out.println();
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!used[i]) {
                used[i] = true;
                ans[index] = arr[i];
                findPermutation(arr, ans, used, index + 1);
                used[i] = false; // backtrack
            }
        }
    }

    // 🔹 3. Permutation for ArrayList
    public static void findPermutation(ArrayList<Integer> list, ArrayList<Integer> ans, boolean[] used) {
        if (ans.size() == list.size()) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!used[i]) {
                used[i] = true;
                ans.add(list.get(i));
                findPermutation(list, ans, used);
                ans.remove(ans.size() - 1); // backtrack
                used[i] = false;
            }
        }
    }
}
