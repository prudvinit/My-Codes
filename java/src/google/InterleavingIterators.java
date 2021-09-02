package src.google;

import java.util.*;

public class InterleavingIterators implements Iterator<Integer>{

    Iterator<Integer>[] iterlist;
    private int ind=0;

    public InterleavingIterators(Iterator<Integer>[] iterlist){
        this.iterlist = iterlist;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5};
        int[] arr3 = {6, 7, 8, 9};
        Iterator<Integer> a = Arrays.stream(arr1).iterator();
        Iterator<Integer> b = Arrays.stream(arr2).iterator();
        Iterator<Integer> c = Arrays.stream(arr3).iterator();
        Iterator<Integer>[] iterlist = new Iterator[]{a,b,c};
        Iterator<Integer> iter = new InterleavingIterators(iterlist);
        while (iter.hasNext())
            System.out.print(iter.next()+" ");
        System.out.println();
    }

    @Override
    public boolean hasNext() {
        for(int i=ind;i<iterlist.length;i++){
            if(iterlist[i].hasNext()){
                ind = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        int t = ind;
        ind = (ind+1)%iterlist.length;
        return iterlist[t].next();
    }
}
