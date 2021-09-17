//https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/
package src.leetcode;

public class NumberOfWaysToPaintAGrid {
    public int numOfWays(int n) {
        return colorTheGrid(3,n);
    }

    static int MOD = 1000000007;

    int[] base(int j, int m){
        int arr[] = new int[m];
        int ind=m-1;
        for(int i=0;i<m;i++){
            arr[ind--] = j%3;
            j=j/3;
        }
        return arr;
    }

    boolean isValid(int j, int m){
        int arr[] = base(j,m);
        for(int i=0;i<m-1;i++) {
            if (arr[i] == arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    boolean isValid(int j, int k, int m){
        int a[] = base(j,m);
        int b[] = base(k,m);
        if(a[m-1]==b[m-1]){
            return false;
        }
        for(int i=0;i<m-1;i++){
            if(a[i]==a[i+1]||b[i]==b[i+1]||a[i]==b[i]){
                return false;
            }
        }
        return true;
    }


    public int colorTheGrid(int m, int n) {
        //For every row, there can be 3^m different combinations
        int max = (int)Math.pow(3,m);
        int dp[][] = new int[n][max];
        //For the starting row, for each valid combination, answer is 1
        for(int j=0;j<max;j++){
            if(isValid(j,m)){
                dp[0][j] = 1;
            }
        }
        //Memorization to store if a combination pair is valid or not
        boolean isValid[][]=new boolean[max][max];
        for(int i=0;i<max;i++){
            for (int j = 0; j < max; j++) {
                isValid[i][j] = isValid(i,j,m);
            }
        }
        //Calculate the dp
        for(int i=1;i<n;i++){
            for(int j=0;j<max;j++){
                //Check if combination k is possible from previous row
                for(int k=0;k<max;k++){
                    //Check if the combo pair is valid
                    if(isValid[j][k]){
                        dp[i][j] = (dp[i][j]+dp[i-1][k])%MOD;
                    }
                }
            }
        }
        //Finally find the total sum
        int ans = 0;
        for(int j=0;j<max;j++){
            ans = (ans+dp[n-1][j])%MOD;
        }
        return ans;
    }
}
