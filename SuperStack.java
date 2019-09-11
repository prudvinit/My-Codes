//https://codejamanalysis.wordpress.com/2017/03/18/crossover-problem-super-stack/

import java.util.Scanner;

class SegmentTree{
    private static final int SIZE = 10;
    int tree[];
    int lazy[];
    int size=0;
    public SegmentTree(){
        tree = new int[(int)Math.pow(2,Math.ceil(Math.log(SIZE)/Math.log(2))+1)];
        lazy = new int[(int)Math.pow(2,Math.ceil(Math.log(SIZE)/Math.log(2))+1)];
    }

    private void update(int start, int end, int x, int left, int right, int ind){
        if(lazy[ind]!=0){
            tree[ind] += (right-left+1)*lazy[ind];
            if(left!=right){
                lazy[2*ind+1] += lazy[ind];
                lazy[2*ind+2] += lazy[ind];
            }
            lazy[ind] = 0;
        }
        if(end<left || start>right){
            return;
        }

        if(start<=left&&end>=right){
            tree[ind] += (right-left+1)*x;
            if(left!=right) {
                lazy[2 * ind + 1] += x;
                lazy[2 * ind + 2] += x;
            }
            return;
        }

        int mid = (left + right)>>1;
        update(start,end,x,left,mid,2*ind+1);
        update(start,end,x,mid+1,right,2*ind+2);
        if(left!=right){
            tree[ind] = tree[2* ind+1]+tree[2*ind+2];
        }
    }


    public void update(int start, int end, int x){
        update(start,end,x,0,SIZE-1,0);
    }
    private int sum(int start, int end, int left, int right, int ind){
        if(lazy[ind]!=0){
            tree[ind] += (right-left+1)*lazy[ind];
            if(left!=right){
                lazy[2*ind+1] += lazy[ind];
                lazy[2*ind+2] += lazy[ind];
            }
            lazy[ind] = 0;
        }
        if(end<left || start>right){
            return 0;
        }

        if(start<=left && end>=right){
            return tree[ind];
//            return;
        }
        int mid = (left+right)>>1;
        int left_ans = sum(start,end,left,mid,2*ind+1);
        int right_ans = sum(start,end,mid+1,right,2*ind+2);
        return left_ans+right_ans;
    }

    public int sum(int start, int end){
        return sum(start,end,0,SIZE-1,0);
    }

    public void push(int x){
        update(size,size,x,0,SIZE-1,0);
        size++;
    }

    public void pop(){
        int get = sum(size-1,size-1);
        update(size-1,size-1,-get,0,SIZE-1,0);
        size--;
    }

    public void lastK(int k, int x){
        update(0,k-1,x);
    }

    public int top(){
        return sum(size-1,size-1);
    }

}
class Main{
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        SegmentTree tree = new SegmentTree();
        int queries = obj.nextInt();
        while(queries-->0) {
            int q = obj.nextInt();
            if (q == 1) {
                tree.push(obj.nextInt());
            } else if (q == 2) {
                tree.pop();
            } else if (q == 3) {
                tree.lastK(obj.nextInt(), obj.nextInt());
            }
            System.out.println(tree.top());
        }
    }
}
