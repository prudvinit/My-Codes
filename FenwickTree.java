import java.util.Scanner;

class FenwickTree{
    //Most important thing to remember is, all the indices are 1-indexed
    public static void update(int BIT[],int pos, int val){
        for(;pos<BIT.length;pos+=pos&-pos){
            BIT[pos]+=val;
        }
    }

    //Find the prefix sum till pos.
    public static int query(int BIT[], int pos){
        int ans = 0;
        for(;pos>0;pos-=pos&-pos){
            ans = ans+BIT[pos];
        }
        return ans;
    }
    public static void main(String[] args) {
        int arr[] = {3,1,4,5,2};
        int BIT[] = new int[arr.length+1];
        for(int i=0;i<arr.length;i++){
            update(BIT,i+1,arr[i]);
        }
        System.out.println("Done");
        update(BIT,2,-1);
        for(int i=0;i<arr.length;i++){
            System.out.println(query(BIT,i+1));
        }
    }
}
