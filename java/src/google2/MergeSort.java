package src.google2;

import java.util.Arrays;

public class MergeSort {

    public static int[] mergeSort(int arr[]){
        if(arr.length<=1)return arr;
        int left[] = mergeSort(Arrays.copyOfRange(arr,0,arr.length/2));
        int right[] = mergeSort(Arrays.copyOfRange(arr,arr.length/2,arr.length));
        int temp[] = new int[arr.length];
        int l=0,r=0,t=0;
        while (l<left.length&&r<right.length){
            temp[t++] = (left[l]<=right[r])?left[l++]:right[r++];
        }
        while (l<left.length){
            temp[t++] = left[l++];
        }
        while (r<right.length){
            temp[t++] = right[r++];
        }
        return temp;
    }
    public static void main(String[] args) {
        int arr[] = {2,5,1,4,3};
        System.out.println(Arrays.toString(mergeSort(arr)));
    }
}
