import java.util.ArrayList;
import java.util.Arrays;


class QuickSort{
    static int pivot(int arr[], int left, int right){
        //take the left most element as the pivotal element
        int pivot = arr[left];
        int start = left;
        int end = right;
        start++;
        while(true){
            //Find the element greater than pivot from the left
            while(start<=end&&arr[start]<=pivot){
                start++;
            }
            //Find the element lower than pivot from the right
            while(start<=end&&arr[end]>=pivot){
                end--;
            }
            //If both the indices cross, break it
            if(start>end){
                break;
            }
            //Swap the start and end indices
            int t = arr[start];
            arr[start] = arr[end];
            arr[end] = t;
        }
        //Swap the pivot element with the first index
        int t = arr[left];
        arr[left] = arr[end];
        arr[end] = t;
        //Return the position
        return end;
    }


    static void quickSort(int arr[], int left, int right){
        if(left>=right){
            return;
        }
        //Find the pivot element
        int pivot = pivot(arr,left,right);
        //Split into left and right halves and recursively sort
        quickSort(arr,left,pivot-1);
        quickSort(arr,pivot+1,right);
    }

    public static void main(String[] args) {
        int arr[] = {31,26,20,17,44,54,77};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
