package src.google2;

public class SegmentTree {
    private int n;
    int tree[];
    int val[];

    public SegmentTree(int val[]){
        this.val = val;
        this.n = val.length;
        tree = new int[(int)Math.pow(2,(int)Math.ceil(Math.log(n)/Math.log(2))+1)-1];
        build(0,n-1,0);
    }

    private void build(int left, int right, int ind){
        if(left==right){
            tree[ind] = val[left];
            return;
        }
        int mid = (left+right)/2;
        build(left,mid,2*ind+1);
        build(mid+1,right,2*ind+2);
        tree[ind] = tree[2*ind+1]+tree[2*ind+2];
    }

    private void update(int left, int right, int ind, int pos, int value){
        if(left==right&&left==pos){
            tree[ind] = value;
            return;
        }
        int mid = (left+right)/2;
        if(pos<=mid){
            update(left,mid,2*ind+1,pos,value);
        }
        else{
            update(mid+1,right,2*ind+2,pos,value);
        }
        tree[ind] = tree[2*ind+1]+tree[2*ind+2];
    }

    public void update(int pos, int value){
        val[pos] = value;
        update(0,n-1,0,pos,value);
    }

    private int get(int left, int right, int ind, int start, int end){
        if(start<=left&&end>=right){
            return tree[ind];
        }
        if(start>right||end<left){
            return 0;
        }
        int mid = (left+right)/2;
        return get(left,mid,2*ind+1,start,end)+get(mid+1,right,2*ind+2,start,end);
    }

    public int get(int start, int end){
        return get(0,n-1,0,start,end);
    }

    public static void main(String[] args) {
        SegmentTree tree = new SegmentTree(new int[]{1,2,3,4,5});
        System.out.println(tree.get(0,4));
        System.out.println(tree.get(1,3));
        tree.update(2,0);
        System.out.println(tree.get(1,3));
        System.out.println(tree.get(1,1));
        System.out.println(tree.get(3,3));

    }
}