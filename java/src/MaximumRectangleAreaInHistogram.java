package src;

import java.util.Arrays;
import java.util.Stack;

public class MaximumRectangleAreaInHistogram {
    public static long getMaxArea(long hist[], long n)
    {
        Stack<Integer> stack = new Stack();
        //Add first index to stack
        stack.push(0);
        long ans = hist[0];
        for(int i=1;i<n;i++){
            //Keep popping as long as top of stack is greater than current value
            while (!stack.isEmpty()&&hist[stack.peek()]>hist[i]){
                int ind = stack.pop();
                ans = Math.max(ans,(stack.isEmpty()?i:(i-stack.peek()-1))*hist[ind]);
            }
            stack.push(i);
        }
        //Deal with remaining stack
        while (!stack.isEmpty()){
            int top = stack.pop();
            ans = Math.max(ans,(stack.isEmpty()?(n):(n-1-stack.peek()))*hist[top]);
        }
        return ans;
    }

    static void test1(){
        long[] arr = new long[]{6,2,5,4,5,1,6};
        long n = arr.length;
        long expected = 12;
        System.out.println("getMaxArea("+ Arrays.toString(arr)+","+n+") = "+getMaxArea(arr,n)+", Expected = "+expected);
    }

    static void test2(){
        long[] arr = new long[]{7,2,8,9,1,3,6,5};
        long n = arr.length;
        long expected = 16;
        System.out.println("getMaxArea("+ Arrays.toString(arr)+","+n+") = "+getMaxArea(arr,n)+", Expected = "+expected);
    }

    static void test3(){
        long[] arr = new long[]{1,2,3,4,5};
        long n = arr.length;
        long expected = 9;
        System.out.println("getMaxArea("+ Arrays.toString(arr)+","+n+") = "+getMaxArea(arr,n)+", Expected = "+expected);
    }

    static void test4(){
        long[] arr = new long[]{3,2};
        long n = arr.length;
        long expected = 4;
        System.out.println("getMaxArea("+ Arrays.toString(arr)+","+n+") = "+getMaxArea(arr,n)+", Expected = "+expected);
    }



    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }
}
