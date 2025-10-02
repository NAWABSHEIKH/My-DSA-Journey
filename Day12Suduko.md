Day 12 Notes – Sudoku Solver (Recursion + Backtracking)
✅ Definition

    Sudoku Solver is a constraint satisfaction problem solved using recursion + backtracking.
    A Sudoku is a 9×9 grid divided into 9 sub-grids (3×3 each).

The goal: Fill all empty cells (denoted as 0) such that:

    Each row contains digits 1–9 without repetition.
    Each column contains digits 1–9 without repetition.
    Each 3×3 box contains digits 1–9 without repetition.

🧠 Core Idea

Start scanning the board for the first empty cell.
    Try numbers 1–9.

For each number:
    If safe → place it → recursively try to solve rest of the board.
    If recursion succeeds → ✅ done.
    If recursion fails → ❌ undo the number (backtrack) and try next number.
    If no number fits → return false (this path is invalid).

📌 Points to Remember

Backtracking pattern:
→ Place → Recurse → If fail → Undo → Try next.

Base Case:
If no empty cell is found → Sudoku is solved → return true.

Safety Checks:
    Must check row, column, and 3×3 subgrid.
Return Values:
    true → solution found, stop further work.
    false → dead end, backtrack.

🛠️ Debugging Method
    Print when you place a number → "Placing X at (row,col)".
    Print when you backtrack → "Backtracking from (row,col)".
    Print final board if solved.

👉 This lets you watch recursion “going forward” and “rewinding”.
📌 How to Recall in Exams/Interviews
    Think of it like filling a crossword puzzle: try → check validity → continue → undo if wrong.

Standard backtracking template:
    for each choice:
        if valid:
            place
            if recursion success: return true
            undo
    return false

Code with Detailed Comments:

    public class SudokuSolver {

        public static void main(String[] args) {
            // Initial Sudoku board (0 means empty cell)
            int[][] board = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
            };

            // Try to solve
            if (solve(board)) {
                printBoard(board); // Print final solution
            } else {
                System.out.println("❌ No solution exists!");
            }
        }

        // Recursive function to solve Sudoku
        static boolean solve(int[][] board) {
            int n = board.length; // board size = 9

            // Traverse the board cell by cell
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {

                    // Found an empty cell
                    if (board[row][col] == 0) {

                        // Try all numbers from 1 to 9
                        for (int number = 1; number <= 9; number++) {
                            // Check if number can be safely placed
                            if (isSafe(board, row, col, number)) {
                                board[row][col] = number; // Place number
                                System.out.println("Placing " + number + " at (" + row + "," + col + ")");

                                // Recursive call → try solving remaining board
                                if (solve(board)) {
                                    return true; // If solved → bubble up success
                                } else {
                                    // Backtrack if placing number didn't lead to a solution
                                    System.out.println("Backtracking from (" + row + "," + col + "), removing " + number);
                                    board[row][col] = 0; // Undo placement
                                }
                            }
                        }

                        // If no number fits in this cell → dead end → backtrack
                        return false;
                    }
                }
            }
            // If no empty cell was found → board is fully solved
            return true;
        }

        // Utility to check if placing num is valid
        static boolean isSafe(int[][] board, int row, int col, int num) {
            // 1. Row check
            for (int j = 0; j < 9; j++) {
                if (board[row][j] == num) return false;
            }

            // 2. Column check
            for (int i = 0; i < 9; i++) {
                if (board[i][col] == num) return false;
            }

            // 3. 3×3 subgrid check
            int startRow = row - row % 3; // Top-left row index of 3×3 box
            int startCol = col - col % 3; // Top-left col index of 3×3 box

            for (int i = startRow; i < startRow + 3; i++) {
                for (int j = startCol; j < startCol + 3; j++) {
                    if (board[i][j] == num) return false;
                }
            }

            // If row, col, and subgrid all allow → safe to place
            return true;
        }

        // Utility function to print final board
        static void printBoard(int[][] board) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

🔑 Key Takeaways

    Backtracking is trial & error with memory. Place → test → undo.
    Sudoku uses the same backtracking template as N-Queens, Word Search, etc.
    Important: Always check row, col, and subgrid before placing.
    Debugging prints are your best tool to visualize recursion.

In interviews, always say:
    “We solve Sudoku using recursion + backtracking. At each empty cell, we try 1–9, check safety, recurse, and backtrack if needed.”