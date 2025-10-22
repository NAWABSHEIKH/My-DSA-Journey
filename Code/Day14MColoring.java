/*
 * 🧩 M-COLORING PROBLEM — COMPLETE EXPLANATION WITH DETAILED COMMENTS
 * ---------------------------------------------------------------
 * Goal:
 * -----
 * Given a graph represented as an edge list, we must determine whether it
 * can be colored using at most M colors such that no two adjacent vertices
 * share the same color.
 *
 * Core Concept:
 * --------------
 * This problem uses a BACKTRACKING approach — we assign colors one by one,
 * and if a color assignment leads to a conflict, we backtrack and try a new color.
 */

import java.util.*;

public class MColoringProblem {

    /*
     * 🔹 Function: graphColoring
     * --------------------------
     * This is the main driver function.
     * It prepares the adjacency list and triggers the backtracking algorithm.
     *
     * Parameters:
     * - edges : 2D array containing the edges of the graph
     * - m     : the maximum number of colors available
     * - V     : number of vertices in the graph
     */
    public static boolean graphColoring(int[][] edges, int m, int V) {

        // Step 1️⃣: Create an adjacency list to represent the graph
        // --------------------------------------------------------
        // We use List<List<Integer>> instead of a 2D array because:
        // 1. Graph size may not be fixed (dynamic)
        // 2. Many nodes may not have connections (sparse graph)
        // 3. It's easier to add neighbors using ArrayList
        
        List<List<Integer>> adj = new ArrayList<>();

        // Initialize empty neighbor lists for each vertex
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Fill adjacency list based on the given edges
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]); // because the graph is undirected
        }

        // Step 2️⃣: Initialize a color array to keep track of assigned colors
        // ------------------------------------------------------------------
        // color[i] = 0 means vertex i is not yet colored.
        // If vertex i is colored, color[i] will store the color number (1 to m).
        int[] color = new int[V];

        // Step 3️⃣: Start recursive backtracking from vertex 0
        return solve(0, color, adj, m, V);
    }

    /*
     * 🔹 Function: solve
     * -------------------
     * This recursive function tries to assign colors to each vertex.
     *
     * Parameters:
     * - node  : current vertex we are trying to color
     * - color : array storing the color of each vertex
     * - adj   : adjacency list (neighbors of each node)
     * - m     : total number of available colors
     * - V     : total number of vertices
     *
     * Logic:
     * ------
     * 1. Base Case: if node == V → all vertices are colored → return true
     * 2. Try all colors (1...m) for this vertex
     * 3. If a color is safe, assign it and recursively color the next vertex
     * 4. If recursion returns true → we found a valid coloring → return true
     * 5. Otherwise, backtrack and try another color
     */
    private static boolean solve(int node, int[] color, List<List<Integer>> adj, int m, int V) {

        // ✅ Base Case: if all vertices are colored successfully
        if (node == V) {
            return true;
        }

        // 🎨 Try assigning each color (from 1 to m) to the current node
        for (int c = 1; c <= m; c++) {

            // Step 1: Check if this color is safe for the current node
            if (isSafe(node, color, adj, c)) {

                // Step 2: Assign this color to the current node
                color[node] = c;

                /*
                 * Step 3: Recursively color the next vertex
                 * ------------------------------------------
                 * If the next vertex can also be colored successfully,
                 * that means our current color choice didn't cause any conflict.
                 *
                 * So, if solve(node + 1, ...) returns TRUE,
                 * it means all vertices from this point onward are colored properly.
                 */
                if (solve(node + 1, color, adj, m, V))
                    return true;  // ✅ SUCCESS PATH FOUND — propagate success upward

                /*
                 * Step 4: BACKTRACK
                 * ------------------
                 * If the recursive call fails, it means our current color 'c'
                 * leads to a wrong configuration later. So we remove it (set to 0)
                 * and try the next color.
                 */
                color[node] = 0;
            }
        }

        // ❌ If no color can be assigned to this node, return false to trigger backtracking
        return false;
    }

    /*
     * 🔹 Function: isSafe
     * ---------------------
     * Checks whether it's safe to assign a given color to a node.
     *
     * Parameters:
     * - node  : current vertex we want to color
     * - color : color array storing the assigned colors of all vertices
     * - adj   : adjacency list (neighbors of each vertex)
     * - c     : the color we are trying to assign to this vertex
     *
     * Why we pass 'c':
     * -----------------
     * Because we are testing whether assigning this particular color
     * (like 1, 2, or 3) to this node will create a conflict with its neighbors.
     *
     * Logic:
     * ------
     * - For every neighbor of the current node, check if it already has color 'c'.
     * - If yes → not safe → return false.
     * - If no neighbor has color 'c' → safe to use → return true.
     */
    private static boolean isSafe(int node, int[] color, List<List<Integer>> adj, int c) {

        // Traverse all neighbors of this node
        for (int neighbor : adj.get(node)) {
            // If any neighbor already has the same color 'c', return false
            if (color[neighbor] == c)
                return false;
        }

        // If no neighbor has this color, it's safe to assign
        return true;
    }

    /*
     * 🔹 Main Method
     * ---------------
     * Used to test the graphColoring() function with example input.
     */
    public static void main(String[] args) {
        // Example graph input
        int V = 4;  // number of vertices
        int[][] edges = {
            {0, 1}, {1, 3}, {2, 3}, {3, 0}, {0, 2}
        };
        int M = 3; // number of available colors

        // Call the main coloring function
        if (graphColoring(edges, M, V))
            System.out.println("✅ Graph can be colored using " + M + " colors.");
        else
            System.out.println("❌ Graph cannot be colored using " + M + " colors.");
    }
}


/*     🧠 Code Summary (in plain English)
    Step	Concept	            Explanation
    1️⃣	    Adjacency List	    Stores neighbors of each node (helps easily check connections).
    2️⃣	    Color Array	        Keeps track of which color each vertex currently has.
    3️⃣	    isSafe()	        Checks if any neighbor already has the color we want to assign.
    4️⃣	    solve()	            Recursively assigns colors; backtracks when conflict arises.
    5️⃣	    Base Case	        When all vertices are colored, return true.
    6️⃣	    Backtracking	    Undo the last color if it leads to a dead end.
    7️⃣	    Return True	        When recursion reaches the end successfully, meaning all nodes are colored without conflict. 

*/