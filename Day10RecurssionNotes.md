📘 N-Queen Problem (Day 10 of Recursion)
✅ Definition
    The N-Queen problem is about placing N queens on an N×N chessboard so that no two queens attack each other.
    A queen can move horizontally, vertically, and diagonally.
    That means, if one queen is placed, no other queen can be in the same row, column, or diagonal.

🧠 Key Insights
    Each row must contain exactly one queen (because otherwise two queens in same row → attack).
    👉 This means we just need to decide in which column to place the queen for each row.

Backtracking helps us explore all possibilities:
    Place a queen in row 0 (try all columns).
    Recursively place queens in the next row.
    If at some point, placement is invalid → backtrack (remove the queen and try next column).

Our goal:
    Either find one valid arrangement.
    Or find all valid arrangements.

🎯 Recursive Structure
    We usually define a function like:
    solveNQueen(board, row)

    If row == N → All queens placed → solution found ✅
    Otherwise:
    Try all columns (col = 0 to N-1)
    If isSafe(board, row, col) → Place queen → Recurse for next row
    Backtrack: Remove queen if further rows fail
    🏰 Safe Placement Check
    For position (row, col), we check:
    No queen in the same column (above rows).
    No queen in upper-left diagonal.
    No queen in upper-right diagonal.

📑 Pseudocode
    boolean solveNQueen(char[][] board, int row) {
        if (row == board.length) {
            printSolution(board);
            return true;  // If you want only 1 solution
        }

        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';  // place queen
                solveNQueen(board, row + 1);
                board[row][col] = '.';  // backtrack
            }
        }
        return false;  // no column worked
    }

🛡 isSafe Function
    boolean isSafe(char[][] board, int row, int col) {
        // check column above
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }

        // check upper-left diagonal
        for (int i = row-1, j = col-1; i>=0 && j>=0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // check upper-right diagonal
        for (int i = row-1, j = col+1; i>=0 && j<board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }

🖼 Example (N=4)

Solutions:

. Q . .       . . Q .
. . . Q       Q . . .
Q . . .       . . . Q
. . Q .       . Q . .

🔍 Why is this important?
    It combines recursion + backtracking.
    Many interviewers use N-Queen to check if you understand constraints + pruning.
    It teaches you how to optimize state checking (using arrays/sets for faster checks).