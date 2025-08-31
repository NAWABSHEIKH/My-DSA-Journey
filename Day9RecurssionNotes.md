Day 9 Notes – Subset / Combination Sum Problem
1. 🔹 Problem Definition
    We are given:
    An array of integers arr[].
    A target value.
    Goal → Find all combinations of numbers from arr that sum up to the target.
    Each number can be used multiple times.
    Order of elements in a combination does not matter (i.e., [2,3,2] is same as [2,2,3] → only one should be counted).
    This is a classic Backtracking / Recursion problem.

2. 🔹 Core Concepts
    Recursion:
    Try to include or exclude each element.
    Reduce the target by the chosen element.
    Stop when target = 0 (valid combination found).
    Backtracking:
    Explore a choice.
    If it leads to a solution → keep it.
    If not → undo the last step and try another choice.
    Branching Factor:
    At each element, we decide whether to pick it or move to next.
    Termination Conditions:
    If target == 0 → print/store the current combination (✅ valid).
    If target < 0 → stop exploring (❌ invalid path).

3. 🔹 Recursive Tree Example
Input:
arr = [2, 3, 6, 7], target = 7
Recursion tree (partial, simplified):

Start: target = 7, ans = []

        ├── Pick 2 → target = 5, ans = [2]
        │     ├── Pick 2 → target = 3, ans = [2,2]
        │     │     ├── Pick 2 → target = 1, ans = [2,2,2] ❌ stop
        │     │     └── Pick 3 → target = 0, ans = [2,2,3] ✅ solution
        │     └── Pick 3 → target = 2, ans = [2,3] ❌ stop
        │
        ├── Pick 3 → target = 4, ans = [3]
        │     ├── Pick 3 → target = 1, ans = [3,3] ❌ stop
        │
        ├── Pick 6 → target = 1, ans = [6] ❌ stop
        │
        └── Pick 7 → target = 0, ans = [7] ✅ solution
✅ Valid solutions: [2,2,3], [7]

4. 🔹 Dry Run
    arr = [2,3,6,7], target = 7

    Step 1 → Start
    ans = [], target = 7

    Step 2 → Choose 2
    ans = [2], target = 5

    Step 3 → Again choose 2
    ans = [2,2], target = 3

    Step 4 → Again choose 2
    ans = [2,2,2], target = 1 → ❌ invalid (stop)

    Step 5 → Backtrack, choose 3
    ans = [2,2,3], target = 0 → ✅ valid

    Step 6 → Backtrack, choose 7
    ans = [7], target = 0 → ✅ valid

 5. 🔹 Check Day9TargetSumSubset program

 6. 🔹 Key Notes for Revision
Difference between Subset & Combination Sum:
    Subset sum: Each element can be used only once.
    Combination sum: Elements can be reused.

    Why start index?
    To prevent duplicates and maintain order (avoid [3,2,2] when [2,2,3] already exists).

    Backtracking step:
    ans.remove(ans.size()-1) ensures we undo the last choice before trying a new one.

    Time Complexity:
    Worst case → Exponential (O(2^n)), because we explore subsets.

    Space Complexity:
    O(target) recursion depth (at most). 

 7. 🔹 Debug

 i) Start: []  (t = 7)
    │
    ├── Add 2 → [2] (t = 5)
    │   │
    │   ├── Add 2 → [2,2] (t = 3)
    │   │   │
    │   │   ├── Add 2 → [2,2,2] (t = 1)
    │   │   │   │
    │   │   │   ├── Add 2 → [2,2,2,2] (t = -1) ❌ Backtrack
    │   │   │   │
    │   │   │   ├── Add 3 → [2,2,2,3] (t = -2) ❌ Backtrack
    │   │   │   │
    │   │   │   ├── Add 6 → [2,2,2,6] (t = -5) ❌ Backtrack
    │   │   │   │
    │   │   │   └── Add 7 → [2,2,2,7] (t = -6) ❌ Backtrack
    │   │   │
    │   │   ├── Add 3 → [2,2,3] (t = 0) ✅ Found solution
    │   │   │
    │   │   ├── Add 6 → [2,2,6] (t = -3) ❌ Backtrack
    │   │   │
    │   │   └── Add 7 → [2,2,7] (t = -4) ❌ Backtrack
    │   │
    │   ├── Add 3 → [2,3] (t = 2)
    │   │   │
    │   │   ├── Add 3 → [2,3,3] (t = -1) ❌ Backtrack
    │   │   │
    │   │   ├── Add 6 → [2,3,6] (t = -4) ❌ Backtrack
    │   │   │
    │   │   └── Add 7 → [2,3,7] (t = -5) ❌ Backtrack
    │   │
    │   ├── Add 6 → [2,6] (t = -1) ❌ Backtrack
    │   │
    │   └── Add 7 → [2,7] (t = -2) ❌ Backtrack
    │
    ├── Add 3 → [3] (t = 4)
    │   │
    │   ├── Add 3 → [3,3] (t = 1)
    │   │   │
    │   │   ├── Add 3 → [3,3,3] (t = -2) ❌ Backtrack
    │   │   │
    │   │   ├── Add 6 → [3,3,6] (t = -5) ❌ Backtrack
    │   │   │
    │   │   └── Add 7 → [3,3,7] (t = -6) ❌ Backtrack
    │   │
    │   ├── Add 6 → [3,6] (t = -2) ❌ Backtrack
    │   │
    │   └── Add 7 → [3,7] (t = -3) ❌ Backtrack
    │
    ├── Add 6 → [6] (t = 1)
    │   │
    │   ├── Add 6 → [6,6] (t = -5) ❌ Backtrack
    │   │
    │   └── Add 7 → [6,7] (t = -6) ❌ Backtrack
    │
    └── Add 7 → [7] (t = 0) ✅ Found solution



ii) Start: []  (target = 7)

                     []
                   (t=7)
     ┌───────────────┼───────────────┐───────────────┐
     v               v               v               v
   [2]             [3]             [6]             [7] ✅
 (t=5)           (t=4)           (t=1)           (t=0)

    -- Explore [2]:
            [2]
            (t=5)
    ┌────────┼────────┐
    v        v        v
    [2,2]   [2,3]    [2,6]
    (t=3)   (t=2)   (t=-1 ❌)

    -- Explore [2,2]:
            [2,2]
            (t=3)
    ┌────────┼────────┐
    v        v        v
    [2,2,2] [2,2,3] [2,2,6]
    (t=1)   (t=0 ✅) (t=-3 ❌)

    -- Explore [2,2,2]:
            [2,2,2]
            (t=1)
            ┌─────┼─────┐
            v     v     v
    [2,2,2,2] [2,2,2,3] [2,2,2,6]
    (t=-1 ❌) (t=-2 ❌) (t=-5 ❌)

    (Backtrack ↑ from dead ends...)

✅ Path found: [2,2,3]

--------

    -- Explore [3]:
            [3]
            (t=4)
    ┌────────┼────────┐
    v        v        v
    [3,3]   [3,6]    [3,7]
    (t=1)   (t=-2 ❌) (t=-3 ❌)

    -- Explore [3,3]:
            [3,3]
            (t=1)
    ┌────────┐
    v        v
    [3,3,3]  [3,3,6]
    (t=-2 ❌)(t=-5 ❌)

(Backtrack ↑)

-------

    -- Explore [6]:
            [6]
        (t=1)
    ┌──────┐
    v      v
    [6,6]   [6,7]
    (t=-5 ❌)(t=-6 ❌)

------

    -- Explore [7]:
            [7]
        (t=0 ✅)

🎉 Path found: [7]

  
