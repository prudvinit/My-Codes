package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        //Sort the intervals based on left and right value
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });
        ArrayList<int[]> ans = new ArrayList();
        int prev[] = intervals[0];
        for(int i=1;i<intervals.length;i++){
            //See if the interval is not overlapping with the previous. Then add the previous to list
            if(intervals[i][0]>prev[1]){
                ans.add(prev);
                prev = intervals[i];
            }
            //If current interval overlaps with previous, then merge both the current interval with previous
            else{
                prev = new int[]{prev[0],Math.max(prev[1],intervals[i][1])};
            }
        }
        ans.add(prev);
        int[][] fans = new int[ans.size()][];
        for(int i=0;i<ans.size();i++){
            fans[i] = ans.get(i);
        }
        return fans;
    }

    static void test1(){
        int[][] arr = new int[][]{ {1,3},{2,6},{8,10},{15,18}};
        int[][] ans = new MergeIntervals().merge(arr);
        for(int i[] : ans){
            System.out.print(Arrays.toString(i)+" ");
        }
        System.out.println();
    }

    static void test2(){
        int[][] arr = new int[][]{ {1,4},{4,5}};
        int[][] ans = new MergeIntervals().merge(arr);
        for(int i[] : ans){
            System.out.print(Arrays.toString(i)+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
