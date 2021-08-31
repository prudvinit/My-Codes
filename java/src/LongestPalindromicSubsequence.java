//https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
package src;

public class LongestPalindromicSubsequence {

    //Utility function to calculate the max of array of integers
    static int max(int... arr){
        int ans = arr[0];
        for(int x : arr)
            ans = Math.max(ans,x);
        return ans;
    }

    static int solve(String s){
        int n = s.length();
        int dp[][] = new int[n][n];
        //For string of length 1, answer is 1
        for(int i=0;i<n;i++){
            dp[i][i] = 1;
        }
        for(int len=2;len<=n;len++){
            for(int i=0;i+len-1<n;i++){
                int j = i+len-1;
                //Update the answer
                dp[i][j] = max(dp[i+1][j],dp[i][j-1],(s.charAt(i)==s.charAt(j)?2:0)+dp[i+1][j-1]);
            }
        }
        return dp[0][n-1];
    }

    static void test(){
        String s = "aba";
        int expected = 3;
        System.out.println(s+", Actual : "+solve(s)+" , Expected : "+expected);
        s = "geeksforgeeks";
        expected = 5;
        System.out.println(s+", Actual : "+solve(s)+" , Expected : "+expected);
        s = "abdba";
        expected = 5;
        System.out.println(s+", Actual : "+solve(s)+" , Expected : "+expected);
    }

    public static void main(String[] args) {
        test();
    }
}
