package bstusingsegmenttree;

import java.io.*;
import java.util.*;

/**
 *
 * @author prudvi.maddala@oracle.com
 */

class BSTSegTree{
    
    int n,tree[],lazy[],bst[][],l[],r[];
    
    public BSTSegTree(int n){
        this.n = n;
        bst = new int[n+1][2];
        l = new int[n+1];
        r = new int[n+1];
        Arrays.fill(l, 1);
        Arrays.fill(r, n);
        int s = (int)Math.pow(2, Math.ceil(Math.log(n+1)/Math.log(2))+1)-1;
        tree = new int[s];
        lazy = new int[s];
        Arrays.fill(lazy, 0);
    }
    
    void updateSegmentTree(int uLeft, int uRight, int val, int left, int right, int current){
        if(lazy[current]!=0){
            tree[current] = lazy[current];
            if(left!=right){
                lazy[2*current+1] = lazy[2*current+2] = lazy[current];
            }
            lazy[current] = 0;
        }
        if(uRight<left||uLeft>right){
            return;
        }
        if(uLeft<=left&&uRight>=right){
            tree[current] = val;
            if(left!=right){
                lazy[2*current+1] = lazy[2*current+2] = val;
            }
            return;
        }
        int mid = (left+right)>>1;
        if(left!=right){
            updateSegmentTree(uLeft, uRight, val, left, mid, 2*current+1);
            updateSegmentTree(uLeft, uRight, val, mid+1, right, 2*current+2);
        }
    }
    
    int getVal(int pos, int left, int right, int current){
        if(lazy[current]!=0){
            tree[current] = lazy[current];
            if(left!=right){
                lazy[2*current+1] = lazy[2*current+2] = lazy[current];
            }
            lazy[current] = 0;
        }
        if(pos<left||pos>right){
            return -1;
        }
        if(pos==left&&pos==right){
            return tree[current];
        }
        int mid = (left+right)>>1;
        return Math.max(getVal(pos,left,mid,2*current+1), getVal(pos,mid+1,right,2*current+2));
    }
    
    void insert(int x){
        int anc = getVal(x, 1, n, 0);
        if(x<anc){
            bst[anc][0] = x;
            this.updateSegmentTree(l[anc], anc-1, x, 1, n, 0);
            l[x] = l[anc];
            r[x] = anc-1;
        }
        else{
            bst[anc][1] = x;
            this.updateSegmentTree(anc+1, r[anc], x, 1, n, 0);
            l[x] = anc+1;
            r[x] = r[anc];
        }
    }
    
    void preOrderTheTree(int u){
        System.out.println(u);
        if(bst[u][0]!=0){
            preOrderTheTree(bst[u][0]);
        }
        if(bst[u][1]!=0){
            preOrderTheTree(bst[u][1]);
        }
    }
}
    
   
public class BSTUsingSegmentTree{

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        InputReader ir = new InputReader();
        int n = ir.readInt();
        BSTSegTree tree = new BSTSegTree(n);
        for(int i=0;i<n;i++){
            tree.insert(ir.readInt());
        }
        tree.preOrderTheTree(0);
    }
}
