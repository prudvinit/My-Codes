//https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
//https://www.youtube.com/watch?v=fAuF0EuZVCk&t=461s
package src;

import java.util.*;

public class KruskalsMST {

    public int find(int[] set, int v){
        if(set[v]==v)return v;
        set[v] = find(set,set[v]);
        return set[v];
    }

    public void union(int[] set, int u, int v){
        set[find(set,v)] = set[find(set,u)];
    }
    public List<int[]> printMST(int graph[][], int V){
        int sets[] = new int[V+1];
        for(int i=0;i<sets.length;i++){
            sets[i] = i;
        }
        //Sort edges on their weight
        Arrays.sort(graph, Comparator.comparingInt(x -> x[2]));
        ArrayList<int[]> mst = new ArrayList();
        //Loop through all edges
        for(int i=0;i<graph.length;i++){
            int[] edge = graph[i];
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            //Check if they are part of same set. If they are part of same set, a cycle is detected
            if(find(sets,u)!=find(sets,v)){
                //If they are not part of the same set, merge those two sets
                union(sets,u,v);
                //Add this edge to MST
                mst.add(edge);
            }
        }
        //Sort the edges, used for testing purposes 
        Collections.sort(mst,Comparator.comparingInt(x->x[2]));
        return mst;
    }

    private static void test1(){
        int graph[][] = {{3,5,14},{1,7,11},{5,4,10},{3,4,9},{1,2,8},{0,7,8},{7,8,7},{2,3,7},{8,6,6},{2,5,4},{0,1,4},{6,5,2},{8,2,2},{7,6,1}};
        int V = 9;
        int ex[][] = new int[][]{{7,6,1},{6,5,2},{8,2,2},{2,5,4},{0,1,4},{2,3,7},{1,2,8},{3,4,9}};
        int ind=0;
        for(int[] e : new KruskalsMST().printMST(graph,V)){
            System.out.println(e[0]+"->"+e[1]+" ("+e[2]+")");
            if(e[0]!=ex[ind][0]||e[1]!=ex[ind][1]||e[2]!=ex[ind][2]){
                System.out.println("TEST FAIL");
                return;
            }
            ind++;
        }
        System.out.println("TEST PASS");
    }

    public static void main(String[] args) {
        test1();
    }
}
