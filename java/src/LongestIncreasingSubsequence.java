//https://leetcode.com/problems/longest-increasing-subsequence/
package src;

import java.util.Arrays;
import java.util.Comparator;

public class LongestIncreasingSubsequence {

    public int patienceSortApproach(int[] nums){
        int n = nums.length;
        Integer t[] = new Integer[n];
        t[0] = nums[0];
        int ans = 1;
        for(int i=1;i<n;i++){
            //Search for the new element.
            int pos = Arrays.binarySearch(t, 0, ans, nums[i], Comparator.comparingInt(o -> o));
            pos = pos<0?-pos-1:pos;
            //Update the new element
            t[pos] = nums[i];
            //If new stack is needed, increment the answer
            if(pos==ans){
                ans++;
            }
        }
        //Print the Longest Increasing Subsequence
        System.out.println(Arrays.toString(Arrays.copyOfRange(t,0,ans)));
        return ans;
    }

    public int lengthOfLIS(int[] nums) {
        return patienceSortApproach(nums);
    }

    static void test1(){
        int[] arr = {10,9,2,5,3,7,101,18};
        System.out.println("LongestIncreasingSubsequence("+Arrays.toString(arr)+") = "+new LongestIncreasingSubsequence().lengthOfLIS(arr));
    }

    public static void main(String[] args) {
        test1();
    }
}
