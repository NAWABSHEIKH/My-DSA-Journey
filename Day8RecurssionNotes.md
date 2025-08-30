Day 8 – Next Permutation (LeetCode #31)
🔹 Problem Definition
Given an array of numbers representing a permutation of integers, rearrange the numbers into the lexicographically next greater permutation.
If such arrangement is not possible (i.e., the array is in descending order), rearrange it as the lowest possible order (ascending sort).

Example
Input: [1,2,3] → Output: [1,3,2]
Input: [3,2,1] → Output: [1,2,3]
Input: [1,1,5] → Output: [1,5,1]

🔹 Intuition Behind the Algorithm
Think of permutations as numbers arranged in dictionary order.
👉 The Next Permutation means:
Find the next larger number formed using the same digits.
If no larger number exists (like [3,2,1]), return the smallest ([1,2,3]).

🔹 Step-by-Step Algorithm
    Find Pivot (Breaking Point)
    Traverse from right to left, find the first index i where arr[i] < arr[i+1].
    This i is the pivot point where the array stops increasing.
    Find Just Greater Element
    From the right side, find the first number larger than arr[i].
    Let that index be j.
    Swap Pivot and Just Greater
    Swap arr[i] and arr[j].
    Reverse Suffix
    Reverse the subarray from i+1 to n-1 (to make it the smallest possible).

🔹 Code with Detailed Comments
    import java.util.*;

    public class Main {
        public static void main(String[] args) {
            int arr[] = {2, 1, 3};  // Input
            nextPermutation(arr);

            System.out.print("Next Permutation Number: ");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
        }

        public static void nextPermutation(int arr[]) {
            int n = arr.length;
            int i = n - 2;

            // Step 1: Find the pivot (first decreasing element from right)
            while (i >= 0 && arr[i] >= arr[i + 1]) {
                i--;
            }

            /*
            Example: arr = [1,3,2]
            Traverse from right:
            arr[1]=3 >= arr[2]=2 ✅ keep going
            arr[0]=1 < arr[1]=3 ❌ stop
            Pivot = index 0 (value = 1)
            */

            // Step 2: If pivot exists, find just greater element from right
            if (i >= 0) {
                int j = n - 1;
                while (j >= 0 && arr[j] <= arr[i]) {
                    j--;
                }
                // Found "just greater" element at j
                swap(arr, i, j);
            }

            /*
            Example: arr = [1,3,2], pivot=0 (value=1)
            From right → arr[2]=2 > 1 ✅ j=2
            Swap arr[0] & arr[2] → [2,3,1]
            */

            // Step 3: Reverse suffix (make it ascending)
            reverse(arr, i + 1, n - 1);

            /*
            Example: After swap [2,3,1]
            Reverse from index 1 → [2,1,3] (next permutation)
            */
        }

        // Utility: swap two elements
        public static void swap(int arr[], int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        // Utility: reverse subarray
        public static void reverse(int arr[], int i, int j) {
            while (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
    }

🔹 Dry Run (Example: [1,3,2])
    Find pivot → 1 (at index 0) because 1 < 3.
    Find just greater → 2 (at index 2).
    Swap → [2,3,1].
    Reverse suffix → [2,1,3].
    ✅ Answer: [2,1,3].

🔹 Debugging Trace
For input: [2,1,3]
    i = 1 → arr[1]=1 < arr[2]=3 → pivot = 1
    Find j from right: arr[2]=3 > 1 → j=2
    Swap arr[1], arr[2] → [2,3,1]
    Reverse from 2 to 2 → [2,3,1]

    ✅ Output: [2,3,1]

🔹 Tree-Like Explanation
    Start: [1,2,3]
    └─ Next Perm: [1,3,2]
        └─ Next Perm: [2,1,3]
            └─ Next Perm: [2,3,1]
                    └─ Next Perm: [3,1,2]
                        └─ Next Perm: [3,2,1]
                            └─ Next Perm: [1,2,3] (reset)


This shows how the algorithm cycles through permutations in lexicographic order.

🔹 How to Explain in Interview
👉 Keep it short, crisp, and logical:
    Scan from right → find first decreasing element (pivot).
    Find the next larger element (to replace pivot).
    Swap them.
    Reverse suffix to make it the smallest possible after pivot.
    Complexity: O(n) time, O(1) space.

🔹 Similar Problems / Applications
    LeetCode 46 – Permutations (generate all permutations, backtracking).
    LeetCode 47 – Permutations II (handle duplicates).
    K-th Permutation Sequence (directly jump to kth permutation).
    Next Greater Element (single number version).
    String rearrangement problems (finding next lexicographic string).