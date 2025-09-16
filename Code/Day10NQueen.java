public class NQueen {
    public static void solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] row  board) {
            Arrays.fill(row, '.');
        }
        backtrack(board, 0);
    }

    public static void backtrack(char[][] board, int row) {
        int n = board.length;

         ✅ Base condition
        if (row == n) {
            printBoard(board);
            System.out.println(----------);
            return;
        }

        for (int col = 0; col  n; col++) {
            if (isSafe(board, row, col)) {
                 PLACE Queen
                board[row][col] = 'Q';
                System.out.println(Placing Queen at Row  + row + , Col  + col);

                 Move to next row
                backtrack(board, row + 1);

                 BACKTRACK
                board[row][col] = '.';
                System.out.println(Removing Queen from Row  + row + , Col  + col);
            }
        }
    }

    public static boolean isSafe(char[][] board, int row, int col) {
        int n = board.length;

         Check column
        for (int i = 0; i  row; i++) {
            if (board[i][col] == 'Q') return false;
        }

         Check diagonal left
        for (int i = row - 1, j = col - 1; i = 0 && j = 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

         Check diagonal right
        for (int i = row - 1, j = col + 1; i = 0 && j  n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }

    public static void printBoard(char[][] board) {
        for (char[] row  board) {
            System.out.println(new String(row));
        }
    }
}
