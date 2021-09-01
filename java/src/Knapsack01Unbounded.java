package src;
//This is o/n knapsack problem, This means, an item can be picked any number of times
public class Knapsack01Unbounded {
    static int knapSack(int N, int W, int val[], int wt[])
    {
        //Initialize dp array
        int dp[] = new int[W+1];
        int ans = 0;
        //Calculate answer for each weight
        for(int i=1;i<=W;i++){
            //For weight i, all weights can be taken
            for(int j=0;j<N;j++){
                //Check if this weight is valid
                if(wt[j]<=i){
                    //Update the answer
                    dp[i] = Math.max(dp[i],val[j]+dp[i-wt[j]]);
                }
            }
            //Update final answer
            ans = Math.max(ans,dp[i]);
        }
        return dp[W];
    }

    static void test1(){
        int N = 2;
        int W = 3;
        int[] val = {1,1};
        int[] weight = {2,1};
        System.out.println(knapSack(N,W,val,weight));
    }
    static void test2(){
        int N = 4;
        int W = 8;
        int[] val = {1,4,5,7};
        int[] weight = {1,3,4,5};
        System.out.println(knapSack(N,W,val,weight));
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
