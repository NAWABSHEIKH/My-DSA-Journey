🧩 M-COLORING PROBLEM — Detailed Concept Note
🖋️ Problem Statement

    Given an undirected graph with V vertices and a number M, determine if it is possible to color all vertices using at most M different colors such that no two adjacent vertices share the same color.

🧠 Real-World Analogy

    Imagine you are designing a map with countries (nodes).
    Two countries sharing a border (edge) cannot have the same color.
    Your task: color the entire map using the least number of colors possible.

📊 Example

Input:

    V = 4
    edges = [[0,1], [1,3], [2,3], [3,0], [0,2]]
    M = 3


This represents the following graph:

      1
     / \
    0---2
     \ /
      3


We must check: can we color this graph with 3 colors so that no two connected nodes have the same color?

🧩 Step-by-Step Understanding
🔹 Step 1: Graph Representation

There are two main ways to represent a graph:
Adjacency Matrix – 2D array (e.g., graph[i][j] == 1 if edge exists)
Adjacency List – List of Lists (e.g., each vertex stores its neighbors)
Here, we use Adjacency List because:
It’s space-efficient for sparse graphs.

Easier to iterate only over actual neighbors.
// Step 1: Convert edges into an adjacency list
List<List<Integer>> adj = new ArrayList<>();
for (int i = 0; i < V; i++) {
    adj.add(new ArrayList<>());
}

for (int[] e : edges) {
    adj.get(e[0]).add(e[1]);
    adj.get(e[1]).add(e[0]); // undirected graph (both directions)
}

🔸 Now, for our input:
edges = [[0,1],[1,3],[2,3],[3,0],[0,2]]

Adjacency list looks like:

    0 → [1, 3, 2]
    1 → [0, 3]
    2 → [3, 0]
    3 → [1, 2, 0]


This means:

    Node 0 is connected to 1, 2, 3

    Node 1 is connected to 0, 3
    and so on.

🔹 Step 2: Backtracking Approach
We will use recursion + backtracking.

Logic:
Start from the first node.
Try coloring it with every color 1...M.
Before assigning, check if this color is safe (no neighbor already has it).
If safe → assign it and move to next node.
If not safe → try next color.
If no color works → backtrack.

🔹 Step 3: “isSafe” Function — The Heart of Logic 💡
    private static boolean isSafe(int node, int[] color, List<List<Integer>> adj, int c) {
        for (int neighbor : adj.get(node)) {
            // If the neighbor already has this color, it's not safe
            if (color[neighbor] == c)
                return false;
        }
        return true;
    }

🔍 How It Works:

Let’s say you’re trying to color node 0 with color 2.
You look at all neighbors of node 0 → [1, 3, 2]
You check: does any neighbor already have color 2?
If yes → return false (can’t use this color)
If no → it’s safe to use this color.

✅ In short:
“A color is safe for a node if none of its adjacent nodes have the same color.”

🔹 Step 4: The Recursive Coloring Function
    private static boolean solve(int node, int[] color, List<List<Integer>> adj, int m, int V) {
        // ✅ Base case: if all nodes are colored
        if (node == V) return true;

        // Try all colors from 1 to m
        for (int c = 1; c <= m; c++) {

            // Check if it's safe to color current node with c
            if (isSafe(node, color, adj, c)) {
                color[node] = c; // assign color

                // Recursively color next node
                if (solve(node + 1, color, adj, m, V))
                    return true;  // ✅ Success path

                // ❌ Backtrack: undo color if it leads to wrong result
                color[node] = 0;
            }
        }

        // If no color can be assigned
        return false;
    }

🔹 Step 5: Driver Function
    public static boolean graphColoring(int[][] edges, int m, int V) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        int[] color = new int[V]; // initially all 0 (uncolored)

        // Start coloring from node 0
        return solve(0, color, adj, m, V);
    }

🔹 Step 6: Main Method
    public static void main(String[] args) {
        int V = 4;
        int[][] edges = {
            {0, 1}, {1, 3}, {2, 3}, {3, 0}, {0, 2}
        };
        int M = 3; // number of colors

        if (graphColoring(edges, M, V))
            System.out.println("✅ Graph can be colored using " + M + " colors.");
        else
            System.out.println("❌ Graph cannot be colored using " + M + " colors.");
    }

🧩 DRY RUN (Step-by-Step)
V = 4
M = 3
edges = [[0,1],[1,3],[2,3],[3,0],[0,2]]

Start → color = [0,0,0,0]

Step	Node	Try Color	Safe?	Action	Color Array
1	    0	1	✅	Assign	[1,0,0,0]
2	    1	1	❌ (neighbor 0=1)	Skip	[1,0,0,0]
3	    1	2	✅	Assign	[1,2,0,0]
4	    2	1	❌ (neighbor 0=1)	Skip	[1,2,0,0]
5	    2	2	❌ (neighbor 3 not yet, but 0=1 safe)	Continue	[1,2,2,0]
6	    2	3	✅	Assign	[1,2,3,0]
7	    3	Try colors...	✅ Works	[1,2,3,1]	

✅ Successfully colored!

🧮 Final Output
✅ Graph can be colored using 3 colors.

💡 Key Takeaways
Concept	What You Learned
    Graph Representation	Adjacency List is memory efficient
    Backtracking	Try → Check → Undo (recursion-based trial and error)
    Safe Check	Ensure no two connected nodes share same color
    Recursion Flow	Base case + recursive case + backtrack
    Visualization	Think in nodes and edges, not arrays
    Debugging	Always dry run with small input
    Pattern	This pattern appears in N-Queens, Sudoku, and subset problems

🧭 Mental Model Summary
    🧩 Graph = Network of Friends
    🎨 Color = Shirt Color of a Friend
    ❌ No Two Friends Connected Should Have Same Shirt Color
    🧠 Try Each Color, Backtrack If Conflict Occurs