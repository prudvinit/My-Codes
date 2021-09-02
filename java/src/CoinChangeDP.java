package src;

import java.util.Arrays;

public class CoinChangeDP {
    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount+1];
        java.util.Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1;i<=amount;i++){
            for(int coin : coins){
                dp[i] = Math.min(dp[i],((i-coin)>=0 && dp[i-coin]!=Integer.MAX_VALUE)?(1+dp[i-coin]):dp[i]);
            }
        }
         System.out.println(Arrays.toString(dp));
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }

    static void test1(){
        int[] coins = new int[]{2};
        int amount = 3;
        System.out.println("coinChange("+ Arrays.toString(coins)+","+amount+") = "+new CoinChangeDP().coinChange(coins,amount));
    }

    public static void main(String[] args) {
        test1();
    }
}