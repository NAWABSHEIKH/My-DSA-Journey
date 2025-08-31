import java.util.*;

public class Main {
    public static void main(String[] args) {
        int arr[] = {2, 3, 2, 7};   // Input array
        int target = 7;             // Target sum
        
        List<Integer> ans = new ArrayList<>();// To store current combination
        
        Arrays.sort(arr); //if you want to avoid duplicate value in that case you need to sort and add line no.39.
        
        combination(arr, ans, 0, target);
        
        
    }
    
    /**
     * Recursive function to find combinations that sum to target.
     * 
     * @param arr     - input array
     * @param ans     - current chosen numbers
     * @param start   - index to start (to avoid duplicates)
     * @param target  - remaining sum to achieve
     */
    public static void combination(int[] arr, List<Integer> ans, int start, int target) {
        
        // ✅ Base case: Target reached → Print the combination
        if (target == 0) {
            System.out.println(ans);
            return;
        }
        
        // ❌ If target goes negative → Stop exploring
        if (target < 0) return;
        
        // 🔄 Explore each candidate from "start" index
        for (int i = start; i < arr.length; i++) {
            
          if (i > start && arr[i] == arr[i - 1]) continue;  // This will help to  skip, adding the duplicate subset.
            
            // 1️⃣ Choose current element
            ans.add(arr[i]);
            
            // 2️⃣ Recurse (we allow same element again → pass "i" not "i+1")
            combination(arr, ans, i, target - arr[i]);
            
            // 3️⃣ Backtrack (remove last element for next choice)
            ans.remove(ans.size() - 1);
        }
    } 
}