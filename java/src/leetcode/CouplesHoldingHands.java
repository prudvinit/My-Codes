//https://leetcode.com/problems/couples-holding-hands/description/
package src.leetcode;

import java.util.Arrays;

public class CouplesHoldingHands {

    int map[] = new int[61];

    void swap(int arr[], int a, int b){
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
        map[arr[a]] = a;
        map[arr[b]] = b;
    }

    //Recursive approach, that gives TLE
    public int minSwapsCouples(int[] row, int pos) {
        if(pos>=row.length)return 0;
        if(row[pos+1]==row[pos]+(row[pos]%2==0?1:-1)){
            return minSwapsCouples(row,pos+2);
        }
        int ind = map[row[pos]+(row[pos]%2==0?1:-1)];
        swap(row,ind,pos+1);
        int a = 1+minSwapsCouples(row,pos+2);
        swap(row,ind,pos+1);
        ind = map[row[pos+1]+(row[pos+1]%2==0?1:-1)];
        swap(row,ind,pos);
        int b = 1+minSwapsCouples(row,pos+2);
        swap(row,ind,pos);
        return Math.min(a,b);

    }

    public int minSwapsCouples(int[] row) {
        for(int i=0;i<row.length;i++){
            map[row[i]] = i;
        }
        int ans = minSwapsCouples(row, 0);
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,2,1,3};
        int expected = 1;
        System.out.println("minSwapsCouples("+ Arrays.toString(arr)+") = "+new CouplesHoldingHands().minSwapsCouples(arr)+", Expected = "+expected);
        arr = new int[]{3,2,0,1};
        expected = 0;
        System.out.println("minSwapsCouples("+ Arrays.toString(arr)+") = "+new CouplesHoldingHands().minSwapsCouples(arr)+", Expected = "+expected);
//        [28,4,37,54,35,41,43,42,45,38,19,51,49,17,47,25,12,53,57,20,2,1,9,27,31,55,32,48,59,15,14,8,3,7,58,23,10,52,22,30,6,21,24,16,46,5,33,56,18,50,39,34,29,36,26,40,44,0,11,13]
        arr = new int[]{28,4,37,54,35,41,43,42,45,38,19,51,49,17,47,25,12,53,57,20,2,1,9,27,31,55,32,48,59,15,14,8,3,7,58,23,10,52,22,30,6,21,24,16,46,5,33,56,18,50,39,34,29,36,26,40,44,0,11,13};
        expected = 0;
        System.out.println("minSwapsCouples("+ Arrays.toString(arr)+") = "+new CouplesHoldingHands().minSwapsCouples(arr)+", Expected = "+expected);
    }
}
