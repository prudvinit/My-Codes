//https://practice.geeksforgeeks.org/problems/egg-dropping-puzzle-1587115620/1
package src;

import java.util.*;
import java.io.*;

class GfG {

    public static void main (String[] args) throws IOException  {

        //reading input using BufferedReader class
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //reading total testcases
        int t = Integer.parseInt(br.readLine().trim());
        while(t-->0){

            //reading number of eggs and floors
            String inputLine[] = br.readLine().trim().split(" ");

            int n = Integer.parseInt(inputLine[0]);
            int k = Integer.parseInt(inputLine[1]);

            //calling eggDrop() method of class
            //EggDrop
            System.out.println(new EggDroppingProblem().eggDrop(n, k));
        }
    }
}


// } Driver Code Ends

public class EggDroppingProblem
{
    //Function to find minimum number of attempts needed in
    //order to find the critical floor.
    //n is eggs and k is floors
    static int eggDrop(int n, int k)
    {
        // Your code here
        int dp[][] = new int[n+1][k+1];
        for(int i=0;i<dp.length;i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        //For 1 egg answer is number of floors
        for(int j=1;j<=k;j++){
            dp[1][j] = j;
        }
        //For 1 floor answer is always 1 and for 0 floors answer is always 0
        for(int i=1;i<=n;i++){
            dp[i][0] = 0;
            dp[i][1] = 1;
        }

        for(int i=2;i<=n;i++){
            for(int j=2;j<=k;j++){
                //Assume egg is dropped from xth floor
                for(int x=1;x<=j;x++){
                    //Worst case is either egg is broken on xth floor i.e dp[i-1][x-1] or egg is not broken on xth floor i.e dp[i][j-x]
                    dp[i][j] = Math.min(dp[i][j],1+Math.max(dp[i-1][x-1],dp[i][j-x]));
                }
            }
        }
        return dp[n][k];
    }
}