public class WordSearchDebug {
    
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        
        System.out.println("Searching for word: " + word);
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    System.out.println("Starting DFS at (" + i + "," + j + ") with letter " + board[i][j]);
                    if (dfs(board, word, i, j, 0)) {
                        System.out.println("✅ Word FOUND in board!");
                        return true;
                    }
                }
            }
        }
        System.out.println("❌ Word NOT found.");
        return false;
    }
    
    private boolean dfs(char[][] board, String word, int row, int col, int index) {
        // Base case: word completely matched
        if (index == word.length()) {
            System.out.println("Word found! ✅");
            return true;
        }
        
        // Out of bounds or mismatch
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length 
            || board[row][col] != word.charAt(index)) {
            return false;
        }
        
        System.out.println("Step " + index + ": Placing '" + word.charAt(index) + 
                           "' at (" + row + "," + col + ")");
        
        // Save character & mark visited
        char temp = board[row][col];
        board[row][col] = '*';
        
        // Explore all 4 directions
        boolean found = dfs(board, word, row - 1, col, index + 1) ||  // up
                        dfs(board, word, row + 1, col, index + 1) ||  // down
                        dfs(board, word, row, col - 1, index + 1) ||  // left
                        dfs(board, word, row, col + 1, index + 1);    // right
        
        // Backtrack
        board[row][col] = temp;
        
        if (!found) {
            System.out.println("Backtracking from (" + row + "," + col + 
                               ") removing '" + word.charAt(index) + "'");
        }
        
        return found;
    }
    
    public static void main(String[] args) {
        WordSearchDebug ws = new WordSearchDebug();
        
        char[][] board = {
            {'A', 'B'},
            {'C', 'D'}
        };
        
        System.out.println(ws.exist(board, "AB"));   // true
        System.out.println("----------------------");
        System.out.println(ws.exist(board, "ACD"));  // false
        System.out.println("----------------------");
        System.out.println(ws.exist(board, "AD"));   // false
    }
}
