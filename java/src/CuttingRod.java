package src;

import java.util.*;

class CuttingRod
{
    //Function to find the maximum number of cuts.
    public int cutRod(int price[], int n) {
        //code here
        int dp[] = new int[n+1];
        Arrays.fill(dp,Integer.MIN_VALUE);
        dp[1] = price[0];
        dp[0]=0;
        //Find minimum cost to cut rod of all lengths from 2 to n
        for(int i=2;i<=n;i++){
            //Cut from 1 to i and check which will give the maximum price
            for(int j=i;j>=1;j--){
                dp[i] = Math.max(dp[i],price[j-1]+dp[i-j]);
            }
        }
        return dp[n];
    }

    static void test1(){
        int N = 8;
        int Price[] = {1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(new CuttingRod().cutRod(Price,N));
    }

    public static void main(String[] args) {
        test1();
    }
}

