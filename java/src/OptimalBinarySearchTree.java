//https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/
package src;

import java.util.Arrays;

public class OptimalBinarySearchTree {

    static int prefixSum(int i, int j, int[] prefix){
        if(i>j){
            return 0;
        }
        if(i<0||i>=prefix.length||j<0||j>=prefix.length){
            return 0;
        }
        return prefix[j]-(i==0?0:prefix[i-1]);
    }
    //Assuming the given array is in sorted order
    static int solve(int arr[], int keys[]){
        int n = arr.length;
        int pref[] = new int[n];
        pref[0] = keys[0];
        for(int i=1;i<n;i++){
            pref[i] = pref[i-1]+keys[i];
        }
        int dp[][] = new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
            dp[i][i] = keys[i];
        }
        for(int len=2;len<=n;len++){
            for(int i=0;i+len-1<n;i++){
                int j = i+len-1;
                int ans = Integer.MAX_VALUE;
                for(int k=i;k<=j;k++){
                    int leftSum = k-1>=i?dp[i][k-1]:0;
                    int rightSum = k+1<=j?dp[k+1][j]:0;
                    int tans = keys[k]+leftSum+rightSum+prefixSum(i,k-1,pref)+prefixSum(k+1,j,pref);
                    ans = Math.min(ans,tans);
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][n-1];
    }

    static void test1(){
        int arr[] = {10,12};
        int freq[] = {34,50};
        System.out.println("For preorder : "+Arrays.toString(arr)+", and Frequency : "+Arrays.toString(freq));
        System.out.println("Actual : "+solve(arr,freq)+" Expected : 118");
    }
    static void test2(){
        int arr[] = {10,12,20};
        int freq[] = {34,8,50};
        System.out.println("For preorder : "+Arrays.toString(arr)+", and Frequency : "+Arrays.toString(freq));
        System.out.println("Actual : "+solve(arr,freq)+" Expected : 142");
    }
    static void test3(){
        int arr[] = {10,12,16,21};
        int freq[] = {4,2,6,3};
        System.out.println("For preorder : "+Arrays.toString(arr)+", and Frequency : "+Arrays.toString(freq));
        System.out.println("Actual : "+solve(arr,freq)+" Expected : 26");
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}
