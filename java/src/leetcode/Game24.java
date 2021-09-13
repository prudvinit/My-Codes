//https://leetcode.com/problems/24-game/
package src.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Game24 {

    //Function to print 2d array
    static void print(int arr[][]){
        for(int i=0;i<arr.length;i++){
            System.out.print(Arrays.toString(arr[i])+" ");
        }
        System.out.println();
    }

    static boolean run(int arr[][]){
        //If only 1 number is present, check if that is 24
        if(arr.length==1){
            return arr[0][0]!=0&&arr[0][0]==24*arr[0][1];
        }
        //Loop through all pairs
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                if(i==j){
                    continue;
                }
                int t[][] = new int[arr.length-1][2];
                int l = 0;
                //Ignore the pair and populate the remaining elements
                for(int k=0;k<arr.length;k++){
                    if(k!=i&&k!=j){
                        t[l++] = arr[k];
                    }
                }
                //For pair, there are 4 possible outcomes. Recurse with each output
                for(int[] val : new int[][]{{arr[i][0]*arr[j][1]+arr[i][1]*arr[j][0],arr[i][1]*arr[j][1]},
                                            {arr[i][0]*arr[j][1]-arr[i][1]*arr[j][0],arr[i][1]*arr[j][1]},
                                            {arr[i][0]*arr[j][0],arr[i][1]*arr[j][1]},
                                            {arr[i][0]*arr[j][1],arr[i][1]*arr[j][0]}}){
                    t[t.length-1] = val;
                    //Check if this combination is valid.
                    if(run(t)){
//                        System.out.print("Parent is ");
                        print(arr);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean judgePoint24(int[] cards) {
        int d[][] = new int[cards.length][2];
        for(int i=0;i<cards.length;i++){
            d[i] = new int[]{cards[i],1};
        }
        return run(d);
    }
    public static void main(String[] args) {
        System.out.println(new Game24().judgePoint24(new int[]{3,3,8,8}));
//        System.out.println(new Game24().judgePoint24(new int[]{1,3,4,6}));
//        System.out.println(new Game24().judgePoint24(new int[]{4,1,8,7}));
//        System.out.println(new Game24().judgePoint24(new int[]{1,2,1,2}));
    }
}
