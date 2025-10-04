🧩 Day 13 Notes: Rat in a Maze Problem
🔹 1. Definition
Problem Statement:
    Given an N x N maze represented by a matrix mat[][] filled with 0s and 1s,

    1 → Path is open (rat can move)
    0 → Blocked cell (rat cannot move)

The rat starts at (0,0) (top-left corner) and needs to reach (N-1, N-1) (bottom-right corner) by moving only:
    Up (U)
    Down (D)
    Left (L)
    Right (R)

We must print all possible paths the rat can take.

🔹 2. Key Idea (Backtracking)
    This problem is solved using Backtracking, which means:
    Try a move.
    If that move leads to a dead end, backtrack (undo the move) and try another.
    Continue until you reach the goal.

💡 In one sentence:
We explore all possible paths, step-by-step, and backtrack whenever we hit a wall or a visited cell.

🔹 3. Full Code (Version 1: Using isVisited Array)
        import java.util.*;

        public class Main {
            public static void main(String[] args) {
                int mat[][] = {
                    {1, 0, 0, 0},
                    {1, 1, 0, 1}, 
                    {1, 1, 0, 0}, 
                    {0, 1, 1, 1}
                };
                
                findPath(mat);
            }

            // Main function to find all paths
            public static void findPath(int[][] mat) {
                ArrayList<String> ans = new ArrayList<>();
                String path = "";
                boolean[][] isVisited = new boolean[mat.length][mat[0].length];

                // Initialize visited matrix as false
                for (boolean[] row : isVisited)
                    Arrays.fill(row, false);

                // Start exploring from (0,0)
                helper(mat, 0, 0, path, ans, isVisited);

                // Print all possible paths
                for (String a : ans)
                    System.out.println(a);
            }

            // Recursive helper function for exploring paths
            public static void helper(int[][] mat, int row, int col, String path,
                                    ArrayList<String> ans, boolean[][] isVisited) {

                // 1️⃣ Base Case: Out of bounds or blocked or already visited
                if (row < 0 || col < 0 || row == mat.length || col == mat[0].length 
                    || mat[row][col] == 0 || isVisited[row][col] == true) {
                    return;
                }

                // 2️⃣ If reached the destination
                if (row == mat.length - 1 && col == mat[0].length - 1) {
                    ans.add(path);
                    return;
                }

                // 3️⃣ Mark the current cell as visited
                isVisited[row][col] = true;

                // 4️⃣ Explore all four directions (D, U, R, L)
                helper(mat, row + 1, col, path + "D", ans, isVisited); // Down
                helper(mat, row - 1, col, path + "U", ans, isVisited); // Up
                helper(mat, row, col + 1, path + "R", ans, isVisited); // Right
                helper(mat, row, col - 1, path + "L", ans, isVisited); // Left

                // 5️⃣ Backtrack step → Unmark visited for other paths
                isVisited[row][col] = false;
            }
        }


🧾 Explanation of Important Steps
| Step | Code Line | Why It's Needed | What If We Don’t Do It? |
|------|------------|----------------|--------------------------|
| **Initialize `isVisited`** | `boolean[][] isVisited` | To prevent infinite recursion (loops). | The rat may go in circles forever between same cells. |
| **Base Case check** | `if (row<0 || col<0 || row==mat.length || col==mat[0].length || mat[row][col]==0 || isVisited[row][col]) return;` | Stops recursion when an invalid cell is reached. | Will cause `ArrayIndexOutOfBounds` or wrong paths. |
| **Mark visited before exploring** | `isVisited[row][col] = true;` | So that we don’t revisit the same cell in the current path. | Causes repetition or infinite recursion. |
| **Backtrack (unmark visited)** | `isVisited[row][col] = false;` | Allows same cell to be reused for *different* paths. | You’ll miss valid paths because the cell remains blocked forever. |
| **Add path on destination** | `ans.add(path);` | Saves path when bottom-right is reached. | You’ll never get any output. |
                            |                                               |                                                  |


🧮 Time & Space Complexity
| Type                 | Explanation                                                                             | Complexity |
| -------------------- | --------------------------------------------------------------------------------------- | ---------- |
| **Time Complexity**  | In the worst case, the rat can explore 4 directions at each cell. So roughly O(4^(N²)). | O(4^(N²))  |
| **Space Complexity** | For recursion stack + visited matrix                                                    | O(N²)      |



🔹 4. Optimized Version (Without isVisited Array)

Instead of maintaining an extra visited[][] array, we can temporarily mark a cell in the original matrix as 0 (blocked) when we visit it and restore it after backtracking.

👉 This saves O(N²) extra space
        import java.util.*;

        public class OptimizedRatInMaze {
            public static void main(String[] args) {
                int mat[][] = {
                    {1, 0, 0, 0},
                    {1, 1, 0, 1}, 
                    {1, 1, 0, 0}, 
                    {0, 1, 1, 1}
                };
                ArrayList<String> ans = new ArrayList<>();
                helper(mat, 0, 0, "", ans);
                
                for (String a : ans)
                    System.out.println(a);
            }

            public static void helper(int[][] mat, int row, int col, String path, ArrayList<String> ans) {
                // Base conditions (same as before)
                if (row < 0 || col < 0 || row == mat.length || col == mat[0].length || mat[row][col] == 0) {
                    return;
                }

                // Destination reached
                if (row == mat.length - 1 && col == mat[0].length - 1) {
                    ans.add(path);
                    return;
                }

                // Mark current cell as visited by setting to 0
                mat[row][col] = 0;

                helper(mat, row + 1, col, path + "D", ans); // Down
                helper(mat, row - 1, col, path + "U", ans); // Up
                helper(mat, row, col + 1, path + "R", ans); // Right
                helper(mat, row, col - 1, path + "L", ans); // Left

                // Backtrack: mark cell as unvisited again
                mat[row][col] = 1;
            }
        }

🧮 Complexity Comparison
 | Version             | Time Complexity | Space Complexity | Uses Extra Space? | Notes                                |
| ------------------- | --------------- | ---------------- | ----------------- | ------------------------------------ |
| With `isVisited`    | O(4^(N²))       | O(N²)            | ✅ Yes             | Safer for beginners, easy to debug   |
| Without `isVisited` | O(4^(N²))       | O(1) (extra)     | ❌ No              | Slightly faster and memory-efficient |
       
🔹 5. Dry Run Example (Simplified 2x2 Maze)
    1 1
    0 1

Paths:
    Start (0,0)
    Move Right → (0,1)

    Move Down → (1,1) → Goal reached ✅
    Path = "RD"

Output:
    RD

🧩 6. Final Takeaways
| Concept           | Description                                                |
| ----------------- | ---------------------------------------------------------- |
| Technique Used    | Backtracking                                               |
| Moves             | D, U, L, R                                                 |
| Base Case         | Invalid cell or goal reached                               |
| Key Step          | Mark visited → Explore → Unmark (Backtrack)                |
| Optimization      | Use matrix itself to mark visited cells                    |
| Real-life Analogy | A rat trying all routes in a maze until it finds all exits |
