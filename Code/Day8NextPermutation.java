import java.util.*;
/*
    1. Find pivot point of number which is decreasing from the behind.
    2. Find just greater number from the pivot point.
    3. Swap the just greater number with the pivot point number.
    4. Reverse the number from the pivot+1 to n-1, to get the next permutation number.
*/
public class Main {
    public static void main(String[] args) {
       
       int arr[]={2,1,3};
       nextPermutation(arr);
       
       System.out.print("Next Permutation Number: ");
       for(int i=0;i<arr.length;i++){
           System.out.print(arr[i]+" ");
       }
    }
    
    public static void nextPermutation(int arr[]){
        
        int n=arr.length;
        int i=n-2;
        
        while(i>=0 && arr[i]>=arr[i+1]){
            i--;
        }
        
 /* 
    Step1)  1>=0=True && 3>=2=True; --> i=0
            0>=0=True && 1>=3=False; --> (do not move inside the loop) 
            Hence, pivot point =0;
            which means arr[pivot]= arr[0]=1 is my point number , which is decreasing from the left;
    */
    
    if(i>=0){
        int j=n-1;
        while(j>=0 && arr[j]<=arr[i]){
            j--;
        }
        swap(arr,i,j);
    }
    
    /*
    step2) if my arr is arranged in decreasing order e.g, {4,3,2} it will never move inside the above loop 
           because the  value of is -1 in that case, which let if condition to be failed  from entering inside the loop.
           
           But considering the mention example {1,3,2} in this case , our i value is 0.
           2>=0==True && 2<=1=False -->Condition failed , it will not decrement the value of j 
           and here we found the number(2) which just greater then pivot number(1). so the value of j is 2 
           
    Step 3) Now we will swap the number for index of pivot point with the value of j 
           
           Final output  {1,3,2}  -->  {2,3,1};
           
         */  
           
       reverse(arr,i+1,n-1);       
           
     /*
    Step 4) Now we will reverse the number from pivot+1 to n-1,to get the next permutation number.
     */      

        
    }
    
    public static void swap(int arr[],int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    } 
    
    public static void reverse(int arr[],int i,int j){
        while(i<j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
        i++;
        j--;
        }
    }
    
    
}
