import java.util.*;

public class NQueen {

    public static void solveNQueens(int n) {
        // Step 1: Create an empty board with '.' representing empty cells
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        // Start backtracking from the first row (row 0)
        backtrack(board, 0);
    }

    public static void backtrack(char[][] board, int row) {
        int n = board.length;

        // ✅ BASE CONDITION:
        // If we placed queens in all rows → solution is ready
        if (row == n) {
            printBoard(board);
            System.out.println("---------- Solution Found ----------");
            return;
        }

        // Try placing queen in each column of current row
        for (int col = 0; col < n; col++) {
            // Check if it's safe to place queen at (row, col)
            if (isSafe(board, row, col)) {

                // ✅ Place queen
                board[row][col] = 'Q';
                System.out.println("Placing Queen at Row " + row + ", Col " + col);

                // 🔁 Recurse to the next row
                backtrack(board, row + 1);

                // ❌ Backtrack: remove queen and try next column
                board[row][col] = '.';
                System.out.println("Removing Queen from Row " + row + ", Col " + col);
            }
            // If not safe → we skip placing here
        }
    }

    // Function to check whether it's safe to place a queen
    public static boolean isSafe(char[][] board, int row, int col) {
        int n = board.length;

        // ✅ Check vertical column (upwards only, because lower rows are empty for now)
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                // If a queen already exists in the same column → conflict
                return false;
            }
        }

        // ✅ Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                // If a queen exists on upper-left diagonal → conflict
                return false;
            }
        }

        // ✅ Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                // If a queen exists on upper-right diagonal → conflict
                return false;
            }
        }

        // If no conflict in col, left diagonal, right diagonal → safe to place
        return true;
    }

    // Utility to print board
    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(new String(row));
        }
    }

    public static void main(String[] args) {
        solveNQueens(4); // Example: Solve for 4 queens
    }
}
