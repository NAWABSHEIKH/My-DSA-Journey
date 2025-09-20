📘 Day 11 – Recursion & Backtracking: Word Search Problem
🔹 Problem Statement
    We are given a 2D grid of characters and a word.
    We need to check if the word can be constructed from sequentially adjacent cells (horizontally or vertically).
    The same cell cannot be reused in the same path.

Example:
    Board:
    A B
    C D

    Word = "AB" → true (A → B path exists)
    Word = "ACD" → false

🔹 Why is this Problem Important?

    Classic recursion + backtracking problem.
    Tests understanding of search space exploration.
    Introduces the DFS pattern on a grid (used in many graph + matrix problems).
    Builds intuition for backtracking → exploring + undoing choices.

🔹 Approach (Step by Step)
1. Brute Force Thinking
    Start at each cell of the grid.
    If the first letter matches → explore in 4 directions.
    Continue until the word is formed or dead end occurs.

2. Why Recursion + Backtracking?
    At each step → we try one letter.
    If it matches → move to next letter.
    If it fails → undo (backtrack) and try another direction.

Avoid revisiting the same cell by temporarily marking it.

🔹 Algorithm (Topper’s Version)
Traverse every cell (i, j) of board.
    If board[i][j] == word.charAt(0), call DFS from that cell.

DFS function:
    Base case: If index == word.length() → found word → return true.
    If out of bounds / mismatch → return false.

Otherwise:
    Place current character.
    Mark current cell as visited (* or boolean).
    Recursively check up, down, left, right for next character.
    If none works → backtrack (restore original char).
    If any DFS returns true → word exists.
    Else → return false.

🔹 Key Debugging Insights
    We added debug prints to understand recursion:
    "Placing letter …" → when trying a path.
    "Backtracking …" → when undoing a wrong choice.
    "Word found!" → when base case reached.

    This makes recursion visible like a movie:
    See where letters are matched.
    Understand how backtracking restores the board.

🔹 What to Revise/Recall
    Recursive DFS Pattern
    Explore → mark → recurse → unmark (backtrack).
    Visited Cells
    Must be marked (to avoid reuse).
    Must be restored (to allow other paths).

Base Condition
    If index == word.length() → success.
    If mismatch / out of bounds → fail.
    Direction Movement
    Always explore 4 directions (up, down, left, right).
    Think of grid DFS as a graph traversal.

🔹 Complexity Analysis

Time Complexity:
    Worst case → O(N × M × 4^L)
    where N = rows, M = cols, L = length of word.
    (At each cell, explore 4 directions for L letters).

Space Complexity:
    O(L) for recursion stack.
    O(1) extra for marking visited (in-place modification).

🔹 Pattern to Remember
    This problem is part of a bigger family of recursion problems:
    Grid + Backtracking (DFS) pattern.

Similar to:
    N-Queens (place → explore → backtrack).
    Sudoku Solver.
    Rat in a Maze.
    Word Search II (multiple words using Trie + backtracking).

Whenever you see:
1)Grid / Matrix
2)Path existence
3)Restrictions on revisiting

 → Think DFS + Backtracking.

🔹 Quick Revision Pointers (One-Liner Notes)
    Start from each cell.
    DFS with index tracking.
    Mark visited, restore after recursion.
    Base case: all characters matched.
    4-direction exploration.

    Backtrack when path fails.

🔹 Example Dry Run (Word = "AB")
    Board:
    A B
    C D

    Start at (0,0) = 'A'
    ✔ Place 'A'
    → Explore (0,1) = 'B'
    ✔ Place 'B'
    Index == word.length → Word found ✅

✅ Final Takeaway
    The Word Search problem is a perfect mix of recursion, backtracking, and DFS.
    If you master this:
    You’ll be comfortable with grid recursion problems.
    It builds direct intuition for Sudoku, Maze, N-Queens, and pathfinding problems.
    This will help you visualize every step like a flowchart inside your brain.

Let’s take a small board for clarity:
    Board:
    A B
    C D

Word = "AB"

🌳 Recursion Tree (DFS for "AB")
    Start DFS from (0,0) → 'A' ✅ (matches word[0])
    Word index = 1 (looking for 'B')

                            [ (0,0)='A' ]
                                    ↓
    ┌───────────────┬───────────────┬───────────────┬───────────────┐
    ↓               ↓               ↓               ↓
    UP(-1,0)       DOWN(1,0)       LEFT(0,-1)     RIGHT(0,1)
    (out of bound) (C ≠ 'B')       (out of bound) (B ✅ match)

    → Dead ❌        → Dead ❌        → Dead ❌          → Continue ✔


    At (0,1) → 'B' ✅ (matches word[1])
    Word index = 2 → reached end of word

    🎉 Word Found → Return TRUE

🔄 Backtracking Visualization
    Enter (0,0) = 'A' → mark visited.
    Explore 4 directions:
    3 fail quickly.
    Right succeeds at (0,1) = 'B'.
    Word found → return true.
    On return → restore board (A unmarked, B unmarked).
    Continue other iterations (but word already found).

🧠 Debug Print Example (Like Movie Playback)
    If you run with debug logs:
    Trying to place 'A' at (0,0)
    Going UP → Out of bound
    Going DOWN → Cell C does not match 'B'
    Going LEFT → Out of bound
    Going RIGHT → Matches 'B'
        Placing 'B' at (0,1)
        Word found! ✅
    Backtracking from (0,1), restoring 'B'
    Backtracking from (0,0), restoring 'A'

This makes recursion crystal clear → explore → match → recurse → backtrack.

✅ Key Understanding From Tree
    At each level of recursion → you branch into 4 possible directions.
    If any path leads to success → propagate TRUE upward.
    Backtracking ensures board state is restored → so other paths remain valid.