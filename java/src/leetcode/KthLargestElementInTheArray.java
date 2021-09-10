//https://leetcode.com/problems/kth-largest-element-in-an-array/
package src.leetcode;

import java.util.Arrays;

//Class to build heap
class MinHeap{

    int[] arr;
    int size;
    //Create an array with capacity k
    MinHeap(int k){
        arr = new int[k];
        size=0;
    }

    void add(int x){
        //If min heap is full
        if(size==arr.length){
            //If element is less than the minimum element, need not insert
            if(x<arr[0]){
                return;
            }
            else{
                //Insert new element at the top
                arr[0] = x;
                int pos = 0;
                //Continue heapification as long as one of the child is lower
                while ((2*pos+1<arr.length&&arr[pos]>arr[2*pos+1])||(2*pos+2<arr.length&&arr[pos]>arr[2*pos+2])){
                    //If no right child, or left child greater than right child, replace current node with left child
                    if(2*pos+2>=arr.length||arr[2*pos+1]<arr[2*pos+2]){
                        int t = arr[pos];
                        arr[pos] = arr[2*pos+1];
                        arr[2*pos+1] = t;
                        pos = 2*pos+1;
                    }
                    //Otherwise, replace the node with the right child
                    else{
                        int t = arr[pos];
                        arr[pos] = arr[2*pos+2];
                        arr[2*pos+2] = t;
                        pos = 2*pos+2;
                    }
                }
            }
        }
        else{
            //Add new element at the end
            arr[size] = x;
            size++;
            int pos = size-1;
            //Keep swapping with parent, if parent is larger
            while (arr[(pos-1)/2]>arr[pos]){
                int t = arr[pos];
                arr[pos] = arr[(pos-1)/2];
                arr[(pos-1)/2] = t;
                pos = (pos-1)/2;
            }
        }
    }
}
public class KthLargestElementInTheArray {
    public int findKthLargest(int[] nums, int k) {
        //create a min heap of max size k
        MinHeap minHeap = new MinHeap(k);
        for(int i=0;i<nums.length;i++){
            //Add all elements to heap
            minHeap.add(nums[i]);
        }
        //Return the top most element
        return minHeap.arr[0];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,2,1,5,6,4};
        int k = 2;
        System.out.println("KthLargest("+ Arrays.toString(arr)+","+k+") = "+new KthLargestElementInTheArray().findKthLargest(arr,k));
        arr = new int[]{3,2,3,1,2,4,5,5,6};
        k = 4;
        System.out.println("KthLargest("+ Arrays.toString(arr)+","+k+") = "+new KthLargestElementInTheArray().findKthLargest(arr,k));
    }
}
