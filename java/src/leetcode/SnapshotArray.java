package src.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SnapshotArray {

    ArrayList<Integer> snaps[];
    ArrayList<Integer> values[];

    int snap = 0,length;

    public SnapshotArray(int length) {
        this.length = length;
        snaps = new ArrayList[length];
        values = new ArrayList[length];
        for(int i=0;i<length;i++){
            snaps[i] = new ArrayList();
            values[i] = new ArrayList();
            snaps[i].add(0);
            values[i].add(0);
        }
    }

    public void set(int index, int val) {
        if(snaps[index].get(snaps[index].size()-1)==snap){
            values[index].set(snaps[index].size()-1,val);
        }
        else{
            snaps[index].add(snap);
            values[index].add(val);
        }
    }

    public int snap() {
        return snap++;
    }

    public int get(int index, int snap_id) {
        int val = Collections.binarySearch(snaps[index],snap_id);
        if(val>=0){
            return values[index].get(val);
        }
        val = Math.max(-val-2,0);
        return values[index].get(val);
    }

    static void test(String[] arr, int a[][]){
        SnapshotArray s = new SnapshotArray(a[0][0]);
        for(int i=1;i<arr.length;i++){
            if(arr[i].equalsIgnoreCase("snap")){
                s.snap();
            }
            if(arr[i].equalsIgnoreCase("set")){
                s.set(a[i][0],a[i][1]);
            }
            if(arr[i].equalsIgnoreCase("get")){
                System.out.println("GET "+s.get(a[i][0],a[i][1]));
            }
            System.out.println("After "+arr[i]+" "+ Arrays.toString(a[i]) );
            System.out.println(Arrays.toString(s.snaps)+" "+Arrays.toString(s.values));
        }
    }

    public static void main(String[] args) {
        test(new String[]{"SnapshotArray","set","snap","set","get"},new int[][]{{3},{0,5},{},{0,6},{0,0}});
        test(new String[]{"SnapshotArray","set","snap","snap","get","get","snap","snap"},new int[][]{{2},{0,12},{},{},{1,0},{1,0},{},{}});
        test(new String[]{"SnapshotArray","snap","snap","get","set","snap","set"},new int[][]{{4},{},{},{3,1},{2,4},{},{1,4}});
    }
}
