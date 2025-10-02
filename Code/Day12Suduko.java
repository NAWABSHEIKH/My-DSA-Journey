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
