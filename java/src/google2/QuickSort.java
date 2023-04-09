package src.google2;

import java.util.Arrays;

public class QuickSort {

    static int partition(int arr[], int left, int right){
        int p = arr[left];
        int start = left,end=right;
//        start++;
        while (true){
            while (start<=end&&arr[start]<=p){
                start++;
            }
            while (start<=end&&arr[end]>=p){
                end--;
            }
            if(start>end)break;
            int t = arr[start];
            arr[start] = arr[end];
            arr[end] = t;
        }
        int t = arr[left];
        arr[left] = arr[end];
        arr[end] = t;
        return end;
    }

    static void quickSort(int arr[], int left, int right){
        if(left>=right)return;
        int p = partition(arr,left,right);
        quickSort(arr,left,p-1);
        quickSort(arr,p+1,right);
    }

    static int[] quickSort(int arr[]){
        quickSort(arr,0,arr.length-1);
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(quickSort(new int[]{3,1,5,2,4})));
    }
}
