package src;

public class Knapsack01 {

    //Function to return max value that can be put in knapsack of capacity W.
    //Knapsack problem with optimal space complexity O(w)
    static int knapSackOptimal(int W, int wt[], int val[], int n)
    {
        int dp[] = new int[W+1];
        for(int i=n-1;i>=0;i--){
            //Note that table is filled from highest to lowest weight.
            for(int j=W;j>=0;j--){
                if(j-wt[i]>=0){
                    dp[j] = Math.max(dp[j],val[i]+dp[j-wt[i]]);
                }
            }
        }
        return dp[W];
    }

    //Knapsack problem with space complexity O(n*W)
    int knapSack(int W, int wt[], int val[], int n)
    {
        int dp[][] = new int[n][W+1];
        int ans = 0;
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<=W;j++){
                //Do not select the weight i
                dp[i][j] = (i+1)<n?dp[i+1][j]:0;
                //Select the weight i
                if(j-wt[i]>=0) {
                    dp[i][j] = Math.max(dp[i][j],val[i] + (i+1<n?(dp[i + 1][j - wt[i]]):0));
                }
                //Update the answer
                ans = Math.max(ans,dp[i][j]);
            }
        }
        return ans;
    }

    static void test1(){
        int W = 4;
        int N = 3;
        int[] weight = new int[]{4,5,1};
        int[] values = new int[]{1,2,3};
        System.out.println(new Knapsack01().knapSack(W,weight,values,N));
    }
    static void test2(){
        int W = 3;
        int N = 3;
        int[] weight = new int[]{4,5,6};
        int[] values = new int[]{1,2,3};
        System.out.println(new Knapsack01().knapSack(W,weight,values,N));
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
