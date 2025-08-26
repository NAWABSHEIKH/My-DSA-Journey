**What is Power Set?**
    The power set of a set is the set of all its subsets, including the empty set and the set itself.
    Example: {1, 2} → [[], [1], [2], [1,2]]

**Challenge with Duplicates**

    If array has duplicates like {1,2,2}, we might generate duplicate subsets.
    Example: two [2] subsets.
    To avoid that, we sort the array and skip duplicates while generating.

**Key Trick Used**
    if (i > index && arr[i] == arr[i - 1]) continue;
    👉 This ensures we don’t pick the same element twice at the same level.

**Time Complexity**
    Without duplicates: O(2^n) subsets.
    With duplicates: still exponential, but skipping avoids redundant work.

**Explanation to Interviewer (Simple)**
i)I generate subsets using recursion + backtracking.
ii)Sorting keeps duplicates together.
iii)Whenever I see the same number again at the same decision level, I skip it.
iv)This ensures unique subsets only.

✅ **Output for input {1,2,2}:**
[[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]

**Problem Reminder**
    We want all subsets of [1,2,2] but no duplicate subsets.

The Code (simplified for understanding)
        import java.util.*;

        public class PowerSetDuplicates {
            public static void main(String[] args) {
                int[] nums = {1, 2, 2};
                Arrays.sort(nums);  // Sort first
                List<List<Integer>> result = new ArrayList<>();
                backtrack(nums, 0, new ArrayList<>(), result);
                System.out.println(result);
            }

            static void backtrack(int[] nums, int start, List<Integer> temp, List<List<Integer>> result) {
                result.add(new ArrayList<>(temp)); // Add current subset

                for (int i = start; i < nums.length; i++) {
                    // SKIP DUPLICATES condition
                    if (i > start && nums[i] == nums[i - 1]) {
                        continue; 
                    }

                    temp.add(nums[i]);                 // choose
                    backtrack(nums, i + 1, temp, result); // explore
                    temp.remove(temp.size() - 1);      // un-choose
                }
            }
        }

**Debugging the Skip Duplicates Condition**
    if (i > start && nums[i] == nums[i - 1]) {
        continue;
    }

    i > start → ensures we only skip duplicates inside the same recursion level.
    (If we didn’t add this, we’d skip valid duplicates in deeper levels.)
    nums[i] == nums[i - 1] → checks if the current number is the same as the previous one.

Together, they mean:
👉** "If I’m at the same recursion level (i > start) and the number is the same as the last one, skip it. Otherwise, allow it."**

**Step-by-Step Walkthrough**
Input: [1, 2, 2]
    Start:
    result = [[]]
    i = 0 → pick 1
    Subset [1] added → result = [[], [1]]

Recurse with start=1:
    i=1 → pick 2
    [1,2] added → result = [[], [1], [1,2]]

Recurse with start=2:
    i=2 → pick 2
    [1,2,2] added → result = [[], [1], [1,2], [1,2,2]]
    (backtrack now)

Back to start=1:
    i=2 → check duplicate:
    nums[2] == nums[1] → both are 2
    and i > start (2 > 1) → YES, skip it.

👉 Prevents duplicate [1,2].
    Back to start=0:
    i=1 → pick 2
    [2] added → result = [[], [1], [1,2], [1,2,2], [2]]

Recurse with start=2:
    i=2 → pick 2
    [2,2] added → result = [[], [1], [1,2], [1,2,2], [2], [2,2]]
    Back to start=0:
    i=2 → duplicate check
    nums[2] == nums[1] → 2 == 2
    and i > start (2 > 0) → NO skip because we are in a different recursion level!
    👉 This ensures we don’t miss [2,2].

Final Output:
[[], [1], [1,2], [1,2,2], [2], [2,2]]


✅ **Key takeaway for interview explanation:**
    The if (i > start && nums[i] == nums[i - 1]) continue; condition is used to skip duplicate choices in the same recursion level, but still allow duplicates in deeper levels where they form unique subsets.

**A tree (recursion tree) will make the “skip duplicates” logic very clear.**
Let’s take the input:
[1, 2, 2]
We first sort → [1, 2, 2] (important for skipping duplicates).
Recursion Tree (Subsets with duplicates handled)
                           []
                  /                     \
            [1]                      skip 1 → []
          /      \                       /      \
      [1,2]      skip 2               [2]       skip 2
    /     \                           /   \
[1,2,2]  skip 2                  [2,2]   skip 2

Step-by-step Explanation
    Start with empty subset [].
    Choice 1: include 1 → go left.
    Choice 2: skip 1 → go right.
    From [1]:
    Include 2 → [1,2]
    Skip this 2 → [1]
    From [1,2]:
    Include next 2 → [1,2,2]
    Skip this 2 → [1,2]

Here’s where duplicate skipping happens!
    If we skip the second 2, we must skip all remaining equal 2s at this level to avoid duplicate subsets.
    From [] branch (skipping 1):
    Include first 2 → [2]
    Skip this 2 → []
    From [2]:
    Include next 2 → [2,2]
    Skip → [2]
    Again, skip logic ensures we don’t generate [2] twice.

Final Subsets Collected
[]
[1]
[1,2]
[1,2,2]
[2]
[2,2]

✅ Key Debug Rule:
**When you skip a number at index i, if nums[i] == nums[i-1], then you continue (skip) instead of generating the same subset again.**