//https://practice.geeksforgeeks.org/problems/word-wrap1646/1
package src;

import java.util.Arrays;

public class TextJustificationDP {
    public int solve(int l[], int k){
        int n = l.length;
        int dp[] = new int[n];
        Arrays.fill(dp,Integer.MAX_VALUE);
        //For last word, answer is always 0
        dp[n-1] = 0;
        //Solve from last to first
        for(int i=n-2;i>=0;i--){
            int lens = 0;
            int cost = 0;
            //From the current index, consider all the words till index j to be part of the current line
            for(int j=i;j<n;j++){
                //Find the total length of all words
                lens+=l[j];
                //Count the total words
                int c = j-i+1;
                //Append spaces to the words and check if sentence length is less than k
                if(lens+c-1<=k){
                    //Find the cost of this line
                    cost =  (k-(lens+c-1))*(k-(lens+c-1));
                    //If this is the last line, cost is always 0
                    cost = j==n-1?0:cost;
                    //Update the cost
                    dp[i] = Math.min(dp[i],cost+(j+1<n?dp[j+1]:0));
                }
                //If total sentence length exceeds k, we need not include other words
                else{
                    break;
                }
            }
        }
        return dp[0];
    }

    static void test1(){
        int arr[] = {3,2,2,5};
        int k = 6;
        System.out.println("solve("+Arrays.toString(arr)+","+k+") = "+new TextJustificationDP().solve(arr,k)+", Expected outcome = 10");
        arr = new int[]{3,2,2};
        k = 4;
        System.out.println("solve("+Arrays.toString(arr)+","+k+") = "+new TextJustificationDP().solve(arr,k)+", Expected outcome = 5");
        arr = new int[]{10,6,5,3,1,10,8,2};
        k = 12;
        System.out.println("solve("+Arrays.toString(arr)+","+k+") = "+new TextJustificationDP().solve(arr,k)+", Expected outcome = 45");
    }

    public static void main(String[] args) {
        test1();
    }
}

