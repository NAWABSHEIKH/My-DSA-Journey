Day 6 – Subsets, Permutations, Combinations (DSA Master Notes)
0) Why these three?
    Most recursion/backtracking interview problems reduce to one of these patterns:
    Subsets: choose/skip each item (power set)
    Combinations: choose k items (order doesn’t matter)
    Permutations: arrange all items (order matters)

    Get these right → you’ll recognize many medium/hard problems instantly.

1) Definitions (super clear)
    Subset (Power Set)
    Any selection of elements (from 0 to n), order does not matter.
    Example for {1,2,3}: [], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]
    Count: 2^n

    Combination (nCk)
    Choose exactly k elements, order does not matter.
    Example for {1,2,3}, k=2: [1,2], [1,3], [2,3]
    Count: nCk = n! / (k!(n-k)!)

    Permutation
    Arrange all (or sometimes k) distinct elements, order matters.
    Example for {1,2,3}: 123, 132, 213, 231, 312, 321
    Count: n! (for full-length permutations)

2) Quick differences (cheat table)
    Topic	Subset	Combination (k)	Permutation
    Order	No	No	Yes
    Size	0..n	Exactly k	Exactly n (or k)
    Count	2^n	nCk	n! (or P(n,k))
    Typical code	include/exclude	for-loop with start idx	backtrack with used[] or swap
    Empty set	Included	Not applicable	Not applicable

3) Mental models / Tricks to remember
    Subsets: At each index → Two choices: pick or don’t pick.
    “Binary decision at each step” → 2^n.
    Combinations: Use a start index. Always move forward (i+1) so you never revisit earlier elements → avoids duplicates like [2,1].
    Permutations: At each level, try all unused options; mark as used → recurse → unmark. Or swap the current index with every index i ≥ idx.

4) Time & Space (typical)
    Subsets: Time O(n·2^n) (visit 2^n subsets, each copy/print ~O(n)); Space O(n) recursion depth.
    Combinations (k): Time O(C(n,k)·k) for output; Space O(k) recursion + O(n) stack worst-case.
    Permutations: Time O(n·n!); Space O(n) recursion depth (+ output).

5) Debug trees (mini)
 i) Subsets of "abc", it will start with empty string then it will either add or skip the character.

                            "" 
                      /               \
                     "a"               ""
                  /     \           /     \
                "ab"     "a"      "b"      ""
                /  \     / \      / \      / \
             "abc" "ab" "ac" "a" "bc" "b" "c" ""

(Leaves are all subsets.)

 ii) Combinations of {1,2,3}, k=2
    Start []
    ├── pick 1 → [1]
    │     ├── pick 2 → [1,2]  ✅
    │     └── pick 3 → [1,3]  ✅
    └── pick 2 → [2]
        └── pick 3 → [2,3]  ✅

 iii) Permutations of "abc"
              []
         /      |       \
      [a]     [b]     [c]
      / \      / \      / \
     ab ac   ba bc    ca  cb
     |  |    |  |     |   |
    abc acb  bac bca  cab cba   ✅

 6)   See the Code of Day6 , there we have explain about Subset with both iterative as well as backtracking approach, permutation and    combination    

 7) Common pitfalls (and fixes)
    ❌ Using depth++ in recursion call (post-increment).
    ✅ Use depth + 1 or pass depth unchanged; prefer idx/start for clarity.

    ❌ For combinations, using used[] without a start index → generates permutations.
    ✅ Use a start parameter and loop for (i = start; i < n; i++).

    ❌ Backtracking remove by value (e.g., list.remove(Integer.valueOf(x))).
    ✅ Remove last added: list.remove(list.size()-1).

    ❌ Copying results incorrectly (adding the same reference).
    ✅ Always add a new copy: res.add(new ArrayList<>(path)).

8) “What to say in an interview” (storyboard)

    “This is a classic backtracking family.
    Subsets: pick/skip → 2^n.
    Combinations: pick k moving forward with start → no duplicates.
    Permutations: try all unused (or swap) → n!.
    I’ll use a path list, add an element, recurse, then remove it (undo) to explore the next branch. I’ll also prune when remaining items can’t fill k.”

Mention time/space and why (number of leaves × cost per output).

9) Practice prompts (fast drills)
    Subsets of a string "abc" (treat chars like array).
    Combinations: choose k=2 from [1,2,3,4].
    Permutations of [1,2,3] using swap (confirm order with tree).
    Try bitmask subsets for arrays up to length 20 (fast iterative).

10) Final recap (one-liners)
    Subsets = pick/skip each index → 2^n.
    Combinations = pick k using start forward → nCk.
    Permutations = choose all orders using used[] or swap → n!.
    Backtracking mantra: choose → explore → undo.   
      

