import java.util.*;

public class RatInMaze {

    public static void main(String[] args) {

        // 1 means path is open, 0 means wall (blocked)
        int[][] maze = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {1, 1, 0, 0},
            {0, 1, 1, 1}
        };

        System.out.println("🐀 Rat in a Maze Problem — Two Approaches\n");

        System.out.println("➡️ APPROACH 1: Using 'visited' matrix (Safer & Clear)");
        solveMazeWithVisited(maze);

        System.out.println("\n➡️ APPROACH 2: Without 'visited' matrix (Space Optimized)");
        solveMazeWithoutVisited(maze);
    }

    /* ------------------------------------------------------------------------------------
       APPROACH 1 — Using Visited Matrix
       This version uses a separate boolean 2D array to mark cells we’ve already visited.
       This prevents the rat from running in circles forever 🌀
    ------------------------------------------------------------------------------------ */
    public static void solveMazeWithVisited(int[][] maze) {
        int n = maze.length;
        ArrayList<String> paths = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];

        // Start exploring from top-left corner (0,0)
        findPathWithVisited(maze, 0, 0, "", visited, paths);

        if (paths.isEmpty()) {
            System.out.println("❌ No path found!");
        } else {
            System.out.println("✅ All possible paths:");
            for (String path : paths) {
                System.out.println(path);
            }
        }
    }

    public static void findPathWithVisited(int[][] maze, int row, int col, String path,
                                           boolean[][] visited, ArrayList<String> paths) {
        int n = maze.length;

        // 🧱 Base Case 1: Out of bounds or blocked cell
        if (row < 0 || col < 0 || row >= n || col >= n || maze[row][col] == 0 || visited[row][col]) {
            return;
        }

        // 🎯 Base Case 2: Reached destination (bottom-right corner)
        if (row == n - 1 && col == n - 1) {
            paths.add(path);
            return;
        }

        // ✅ Mark current cell as visited so we don’t visit again in same path
        visited[row][col] = true;

        // 🧭 Move Down (D)
        findPathWithVisited(maze, row + 1, col, path + "D", visited, paths);
        // 🧭 Move Up (U)
        findPathWithVisited(maze, row - 1, col, path + "U", visited, paths);
        // 🧭 Move Right (R)
        findPathWithVisited(maze, row, col + 1, path + "R", visited, paths);
        // 🧭 Move Left (L)
        findPathWithVisited(maze, row, col - 1, path + "L", visited, paths);

        // 🌀 BACKTRACK — unmark cell before returning
        // so other paths can use it again
        visited[row][col] = false;
    }

    /* ------------------------------------------------------------------------------------
       APPROACH 2 — Without Visited Matrix
       We modify the original maze temporarily to mark visited cells (set them to 0),
       and then restore them (set back to 1) while backtracking.
       This saves extra space but changes the maze temporarily.
    ------------------------------------------------------------------------------------ */
    public static void solveMazeWithoutVisited(int[][] maze) {
        int n = maze.length;
        ArrayList<String> paths = new ArrayList<>();

        findPathWithoutVisited(maze, 0, 0, "", paths);

        if (paths.isEmpty()) {
            System.out.println("❌ No path found!");
        } else {
            System.out.println("✅ All possible paths:");
            for (String path : paths) {
                System.out.println(path);
            }
        }
    }

    public static void findPathWithoutVisited(int[][] maze, int row, int col,
                                              String path, ArrayList<String> paths) {
        int n = maze.length;

        // 🧱 Base Case 1: Out of bounds or blocked cell
        if (row < 0 || col < 0 || row >= n || col >= n || maze[row][col] == 0) {
            return;
        }

        // 🎯 Base Case 2: Destination reached
        if (row == n - 1 && col == n - 1) {
            paths.add(path);
            return;
        }

        // 🚧 Mark this cell as visited (set to 0)
        maze[row][col] = 0;

        // Explore all directions
        findPathWithoutVisited(maze, row + 1, col, path + "D", paths); // Down
        findPathWithoutVisited(maze, row - 1, col, path + "U", paths); // Up
        findPathWithoutVisited(maze, row, col + 1, path + "R", paths); // Right
        findPathWithoutVisited(maze, row, col - 1, path + "L", paths); // Left

        // 🌀 BACKTRACK — restore path (set cell back to 1)
        maze[row][col] = 1;
    }
}
