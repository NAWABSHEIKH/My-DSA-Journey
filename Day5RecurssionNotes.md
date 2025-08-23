Day 5 – Subsets vs Permutations (Final Notes)
🔹 Subset Definition
    A subset is any selection of elements from a set, where order does not matter.
    Example: For set {1,2,3}, subsets include {}, {1}, {1,2}, {2,3}, {1,2,3}.

If there are n elements, total subsets = 2^n.
🔹 Permutation Definition
    A permutation is an arrangement of all elements of a set, where order matters.
    Example: For {1,2,3}, permutations are:
    123, 132, 213, 231, 312, 321.

If there are n elements, total permutations = n! (factorial of n).

⚖️ Difference Between Subset and Permutation
    Feature	         Subset	                            Permutation
    Order	         Does not matter	                Matters
    Count	         2^n possible subsets               n! possible permutations
    Includes Empty?	 Yes (empty set is a subset)        No (permutation uses all elements)
    Example (n=3)	 {}, {1}, {2,3}, {1,2,3}	        123, 132, 213, 231, 312

Difference between Subset vs Permutation

    Subset problem:
    For each element → you decide “take it” or “don’t take it”.
    (So only 2 choices per step.)

    Permutation problem:
    You have to arrange all numbers in every possible order.
    At each step → you can pick any unused element.
    (So if you have n numbers, first step = n choices, next step = n-1 choices, etc.)

Example with [1,2,3]

    Imagine you have 3 empty slots: [ _ , _ , _ ].
    First slot → you can put either 1 or 2 or 3.
    If you put 1, now remaining = {2,3}.
    If you put 2, now remaining = {1,3}.
    If you put 3, now remaining = {1,2}.

    Second slot → from the remaining two, you again have 2 choices.
    Third slot → only 1 element left, so it must go there.
    So the tree looks like this:

            Start: [ _ , _ , _ ]
            ├── 1 → [1, _ , _]
            │    ├── 2 → [1, 2, _] → then 3 → [1, 2, 3]
            │    └── 3 → [1, 3, _] → then 2 → [1, 3, 2]
            ├── 2 → [2, _ , _]
            │    ├── 1 → [2, 1, _] → then 3 → [2, 1, 3]
            │    └── 3 → [2, 3, _] → then 1 → [2, 3, 1]
            └── 3 → [3, _ , _]
                ├── 1 → [3, 1, _] → then 2 → [3, 1, 2]
                └── 2 → [3, 2, _] → then 1 → [3, 2, 1]


    That’s how all 3! = 6 permutations are generated.
    💡 The core idea =
    Subset → binary decision (take/skip).
    Permutation → multi-choice decision (pick one unused element).


Difference between Subset & Permutation
    Subset problem → At each index, you decide: include or exclude → recursion tree branches with choices yes/no.
    Permutation problem → You need to use all elements but in every possible order.

    How to Think for Permutations (Hint)
    At any step, you pick one element from the remaining list.
    Then recursively generate permutations for the rest of the elements.
    Continue until no elements are left → that forms one complete permutation.

👉 Example with [1,2,3]:
        First step: pick 1 → remaining [2,3] → generate permutations of [2,3].
        Pick 2 → remaining [1,3] → generate permutations of [1,3].
        Pick 3 → remaining [1,2] → generate permutations of [1,2].

⚡ The key difference from subsets is:
        In subsets → decision is yes/no for each element.
        In permutations → decision is which element to pick next.

Let’s sketch the recursion tree for [1,2,3] permutations.
        Step 1: First level (pick 1, 2, or 3)
                            []
                    /       |         \
                [1]        [2]        [3]
        Step 2: Second level (pick from remaining numbers)
        From [1], you can pick 2 or 3
        From [2], you can pick 1 or 3
        From [3], you can pick 1 or 2
                            []
                    /       |         \
                [1]        [2]        [3]
                /    \      /    \      /    \
            [1,2] [1,3] [2,1] [2,3] [3,1] [3,2]

        Step 3: Third level (pick the last remaining number)
        Now only one number is left each time, so add it:
                            []
                    /       |         \
                [1]        [2]        [3]
                /    \      /    \      /    \
            [1,2] [1,3] [2,1] [2,3] [3,1] [3,2]
                |      |     |      |     |     |
            [1,2,3][1,3,2][2,1,3][2,3,1][3,1,2][3,2,1]

Final permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]


