package src;

import java.util.Arrays;

public class PriorityQueuePlus{
    int n;
    int heap[];
    int size = 0;
    int[][] map;
    PriorityQueuePlus(int n){
        this.n = n;
        heap = new int[n+1];
        map = new int[n+1][2];
        for(int i=0;i<=n;i++){
            map[i][0] = -1;
        }
    }

    private void exchange(int a, int b){
        int t = heap[a];
        heap[a] = heap[b];
        heap[b] = t;
        map[heap[a]][0] = a;
        map[heap[b]][0] = b;
    }

    public void add(int node, int value){
        int ins = size;
        if(map[node][0]==-1) {
            heap[size++] = node;
            map[node][0] = size-1;
            map[node][1] = value;
        }
        else{
            ins = map[node][0];
            map[node][1] = value;
        }
        while(ins>0&&map[heap[(ins-1)/2]][1]>map[heap[ins]][1]){
            exchange((ins-1)/2,ins);
            ins = (ins-1)/2;
        }
    }

    public int remove(){
        int ans = heap[0];
        heap[0] = heap[size-1];
        map[heap[0]][0] = 0;
        int i = 0;
        while ((2*i+1<size&&map[heap[2*i+1]][1]<map[heap[i]][1])||(2*i+2<size&&map[heap[2*i+2]][1]<map[heap[i]][1])){
            if(2*i+2>=size||map[heap[2*i+1]][1]<map[heap[2*i+2]][1]){
                exchange(i,2*i+1);
                i=2*i+1;
            }
            else{
                exchange(i,2*i+2);
                i=2*i+2;
            }
        }
        size--;
        return ans;
    }

    public boolean isEmpty(){
        return size==0;
    }


    public static void main(String args[]){
        PriorityQueuePlus pq = new PriorityQueuePlus(10);
        for(int x : new int[]{5,1,2,7,3}){
            pq.add(x,x);
        }
        System.out.println(Arrays.toString(pq.heap));
        for(int i=1;i<=5;i++)
            System.out.println(Arrays.toString(pq.map[i]));
//        pq.add(5,-10);
//        pq.add(3,-100);
        for(int i=0;i<5;i++) {
            System.out.println(pq.remove());
        }
    }
}