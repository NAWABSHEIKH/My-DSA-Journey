📌 Backtracking Basics
What is Backtracking?
    Backtracking = Recursion + Undo.
    Recursion → explores possibilities.
    Backtracking → undoes a choice before moving to the next one.

👉 Formula: Try → Explore → Undo → Backtrack
Difference: Recursion vs. Backtracking
Feature	    Recursion.	                                Backtracking.
Goal	    Solve by breaking into smaller subproblems.	Solve by exploring all possible choices.
Choices     Handling	Does not undo choices.	        Always undoes before moving to next choice.
Example	    Fibonacci, Factorial.	                    Maze solving, N-Queens, Sudoku.

⚡ Classic Backtracking Problems
        Rat in a Maze 🐭 → Find all possible paths.
        N-Queens 👑 → Place queens on a chessboard without attack.
        Subset / Power Set Generation → Explore all combinations.
        String Permutations → All possible arrangements.
        Sudoku Solver (advanced) → Fill valid numbers in grid.


Classic Backtracking Problems
    1.Rat in a Maze 🐭
        Find all paths from start → destination.
        Example: Move only in Right or Down.
        Code explores → marks path → backtracks if blocked.

    2.N-Queens 👑
        Place N queens on an N×N board so that no two attack each other.
        Requires pruning: skip invalid placements.
        Subset Generation (Power Set)
        Each element has 2 choices: include or exclude.
        Generates all subsets of a set.

    3.String Permutations
        Generate all possible orderings of characters.
        Swap → Recurse → Swap back (undo).



🧩 Patterns to Identify Backtracking Problems
        Multiple choices at each step.
        Need to explore all options.
        Must undo the choice before trying next.
        Often phrased as “generate all possibilities”.

🎯 Learning Outcomes (Today’s Focus)
    Think step-by-step while exploring all possibilities.
    Write clean recursive code.
    Optimize using pruning (skip invalid states early).
    Build habit of dry-run tracing for clarity.

📝 Plan for Day 4
    Concept (30 min) → Recap recursion vs backtracking.
    Problem 1 (45 min) → Rat in a Maze → print all paths.
    Problem 2 (45 min) → Subset Generation.
    Stretch Goal → N-Queens problem (most famous).

🌱 Pro Tips for Interview Prep
Explain thought process: interviewer cares more about how you explore possibilities than just the code.
Use base case + choice + backtrack pattern:
        void solve(choice[]) {
            if(base condition) {
                print/return;
                return;
            }
            for(each option) {
                make choice;
                solve(next);
                undo choice;  // backtrack step
            }
        }


a) Subsets of a String:
        At each character, you have two choices: include it or exclude it.
        This builds a binary decision tree with 2^n leaves (all subsets).

                public class StringSubsets {
                public static void printSubsets(String str, String ans) {
                    if (str.length() == 0) {
                        System.out.println(ans);
                        return;
                    }
                    char ch = str.charAt(0);
                    String rest = str.substring(1);

                    // Include current char
                    printSubsets(rest, ans + ch);

                    // Exclude current char
                    printSubsets(rest, ans);
                }

                public static void main(String[] args) {
                    printSubsets("abc", "");
                }
            }

Debugging:
                          ("",0)
                     /               \
              Exclude 'a'          Include 'a'
              ("",1)                  ("a",1)
            /       \                /       \
     Ex 'b'       In 'b'       Ex 'b'       In 'b'
     ("",2)       ("b",2)      ("a",2)      ("ab",2)
     /   \        /     \      /     \      /     \
Ex 'c'  In 'c' Ex 'c'  In 'c' Ex 'c' In 'c' Ex 'c' In 'c'
(""3) ("c"3) ("b"3)("bc"3) ("a"3)("ac"3)("ab"3)("abc"3)


⏱️ Complexity

Time: O(n · 2^n) (2^n subsets, up to n work per output)
Space: O(n) (recursion depth)
⚙️ Tip: If you’re building strings heavily, consider StringBuilder to reduce temporary objects.

b) Subsets of Numbers (Power Set)
Use backtracking with an index. At each step, choose the next element(s) to add; add → recurse → remove.

                    import java.util.*;
            public class NumberSubsets {
                static List<List<Integer>> result = new ArrayList<>();

                public static void subsets(int[] nums) {
                    backtrack(nums, 0, new ArrayList<>());
                    System.out.println(result);
                }

                private static void backtrack(int[] nums, int start, List<Integer> cur) {
                    result.add(new ArrayList<>(cur));           // take current snapshot

                    for (int i = start; i < nums.length; i++) {
                        cur.add(nums[i]);                       // choose
                        backtrack(nums, i + 1, cur);            // explore
                        cur.remove(cur.size() - 1);             // undo
                    }
                }

                public static void main(String[] args) {
                    subsets(new int[]{1, 2, 3});
                }
            }
 
🐞 Dry Run (nums = [1,2])

        Start: []
        Take 1 → [1]
        Take 2 → [1,2] → backtrack → [1]
        Backtrack to []
        Take 2 → [2] → backtrack → []
        Result: [], [1], [1,2], [2]

  🔁 Handling Duplicates
        Sort first; when you’re at the same recursion level, skip equal neighbors.

            import java.util.*;

            public class NumberSubsetsWithDup {
                static List<List<Integer>> result = new ArrayList<>();

                public static void subsetsWithDup(int[] nums) {
                    Arrays.sort(nums);
                    backtrack(nums, 0, new ArrayList<>());
                    System.out.println(result);
                }

                private static void backtrack(int[] nums, int start, List<Integer> cur) {
                    result.add(new ArrayList<>(cur)); // snapshot
                    for (int i = start; i < nums.length; i++) {
                        if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicate at this depth
                        cur.add(nums[i]);
                        backtrack(nums, i + 1, cur);
                        cur.remove(cur.size() - 1);
                    }
                }

                public static void main(String[] args) {
                    subsetsWithDup(new int[]{1, 2, 2});
                }
            }




One-liners to Remember
    Subset (string): include/exclude each char → binary decision tree.
    Subset (numbers): for-loop from start → choose, recurse, undo.
    Duplicates: sort + skip when i > start && nums[i] == nums[i-1].
    Bitmasking: elegant iterative alternative with same complexity.

