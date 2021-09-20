//https://leetcode.com/problems/odd-even-jump/

//INCOMPLETE
package src.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class OddEvenJump {
    int BIT[];
    int original[];

    void update(int pos, int val){
        for(;pos<BIT.length;pos+=pos&-pos){
            BIT[pos]+=val;
        }
    }

    //Find the prefix sum till pos.
    int get(int pos){
        int ans = 0;
        for(;pos>0;pos-=pos&-pos){
            ans = ans+BIT[pos];
        }
        return ans;
    }

//    void update(int pos, int val){
//        original[pos]+=val;
//        for(;pos<BIT.length;pos+=pos&-pos){
//            System.out.println(pos);
//            BIT[pos]+=val;
//        }
//    }
//    int get(int pos){
//        int ans = 0;
//        for(;pos>0;pos-=pos&-pos){
//            ans += BIT[pos];
//        }
//        return ans;
//    }
    int get(int left, int right){
        int ans = get(right);
        if(left!=0){
            ans = ans-get(left-1);
        }
        return ans;
    }

    public int oddEvenJumps(int[] arr) {
        int n = arr.length;
        int nextHighest[] = new int[n];
        int nextLowest[] = new int[n];
        Integer ind[] = new Integer[n];
        for(int i=0;i<n;i++){
            ind[i] = i;
        }
        Arrays.sort(ind, Comparator.comparingInt(a -> arr[a]));
        BIT = new int[n+1];
        original = new int[n+1];
        System.out.println(Arrays.toString(ind));
        update(ind[n-1]+1,1);
        original[ind[n-1]+1] = 1;
        for(int i=n-2;i>=0;i--){
            int pos = ind[i]+1;
            int left = pos+1;
            int right = BIT.length-1;
            int mid = (left+right)/2;
            while (left<=right){
                int s = get(pos+1,mid);
                if(s==1&&original[mid]==1){
                    nextHighest[ind[i]] = mid-1;
                    break;
                }
                if(s==0){
                    left = mid+1;
                }
                else{
                    right = mid-1;
                }
                mid = (left+right)/2;
            }
            update(pos,1);
            original[pos] = 1;
        }

        Arrays.sort(ind, Comparator.comparingInt(a -> -arr[a]));
        BIT = new int[n+1];
        original = new int[n+1];
        System.out.println(Arrays.toString(ind));
        update(ind[n-1]+1,1);
        original[ind[n-1]+1] = 1;
        for(int i=n-2;i>=0;i--){
            int pos = ind[i]+1;
            int left = pos+1;
            int right = BIT.length-1;
            int mid = (left+right)/2;
            while (left<=right){
                int s = get(pos+1,mid);
                if(s==1&&original[mid]==1){
                    nextHighest[ind[i]] = mid-1;
                    break;
                }
                if(s==0){
                    left = mid+1;
                }
                else{
                    right = mid-1;
                }
                mid = (left+right)/2;
            }
            update(pos,1);
            original[pos] = 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new OddEvenJump().oddEvenJumps(new int[]{10,13,12,14,15}));
       OddEvenJump e = new OddEvenJump();
       e.BIT = new int[6];
       e.update(5,1);
        System.out.println(e.get(5)+" "+e.get(4));
    }
}
