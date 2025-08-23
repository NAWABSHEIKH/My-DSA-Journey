/*📘 Subset Generation Problem
❓ Problem Statement
We are asked to print all subsets of a given string.
A subset means a selection of characters (possibly none, possibly all).
Order matters only for recursion traversal, but final output is the power set of the string.

For example:
👉 Input: "abc"
👉 Output subsets: 
     "", "c", "b", "bc", "a", "ac", "ab", "abc"

(Empty string "" is also a valid subset)

Approach: Recursion + Decision Tree
    At each character, we have two choices:
    Include the character in the current subset.
    Exclude the character from the current subset.
    This creates a binary recursion tree with depth = string length.

*/




public class SubsetExample {
    public static void main(String[] args) {
        String input = "abc";
        printSubsets(input, "", 0);
    }

    // Recursive function to print all subsets
    static void printSubsets(String str, String current, int index) {
        // Base case: if we've processed all characters
        if (index == str.length()) {
            System.out.println(current);
            return;
        }

        // Choice 1: Exclude current character
        printSubsets(str, current, index + 1);

        // Choice 2: Include current character
        printSubsets(str, current + str.charAt(index), index + 1);
    }
}

/*
🔍 Dry Run for "abc"

We build a recursion tree step by step:

                                 ("", index=0)
                       /                               \
                  Exclude 'a'                        Include 'a'
                 ("", index=1)                      ("a", index=1)
              /                 \                /                   \
        Exclude 'b'         Include 'b'     Exclude 'b'           Include 'b'
       ("", index=2)       ("b", index=2)   ("a", index=2)       ("ab", index=2)
      /         \           /       \       /         \           /          \
 Ex 'c'     In 'c'    Ex 'c'   In 'c'   Ex 'c'   In 'c'    Ex 'c'     In 'c'
("",3)   ("c",3)   ("b",3) ("bc",3)  ("a",3)("ac",3)  ("ab",3) ("abc",3)

📤 Final Output (Leaf Nodes)
From the above recursion tree, we collect outputs when index == str.length() (base case reached):

"" , "c" , "b" , "bc" , "a" , "ac" , "ab" , "abc"


👉 This is the power set of "abc".
⏱️ Time & Space Complexity
Time Complexity:
Each character has 2 choices → total subsets = 2^n.


For "abc" → 2^3=8
Time = O(2^n).

Space Complexity:
Recursion depth = n (max call stack frames).
Current subset string space = O(n).
Space = O(n)
Key Points for Interview
Problem = Generate all subsets (Power Set).
Each step = Include or Exclude choice → binary recursion tree.


Dry run shows recursion exploration order.
Space = O(n), Time = O(2^n).

 */