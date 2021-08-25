package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class TarzansBridgeFindingAlgorithm {

    public void runDFS(int v, int parent, ArrayList<ArrayList<Integer>> adj, boolean visited[], int disc[], int low[], int timer){
        //Update the visited. low and discovery times
        visited[v] = true;
        low[v] = timer;
        disc[v] = timer;
        //Loop through all child nodes
        for(int child : adj.get(v)){
            //Do not act on parent
            if(child!=parent) {
                //if not visited, perform dfs on child with the updated timer
                if (!visited[child]) {
                    runDFS(child, v, adj, visited, disc, low, timer + 1);
                }
                //Once the child is traversed, update the parent's low time
                low[v] = Math.min(low[v], low[child]);
                //Check if low of child is greater than discovery of parent
                if (low[child] > disc[v]) {
                    System.out.println("Bridge : " + v + " " + child);
                }
            }
        }
    }

    public void findBridges(int V, ArrayList<ArrayList<Integer>> adj)
    {
        boolean visited[] = new boolean[V];
        int disc[] = new int[V];
        int low[] = new int[V];
        Arrays.fill(low,Integer.MAX_VALUE);
        for(int i=0;i<V;i++) {
            if(!visited[i]) {
                runDFS(i, -1, adj, visited, disc, low, 0);
            }
        }
    }

    static ArrayList<ArrayList<Integer>> makeGraph(int V, int g[][]){
        ArrayList<ArrayList<Integer>> graph = new ArrayList();
        for(int i=0;i<V;i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                if(g[i][j]==1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        return graph;
    }

    static void test1(){
        System.out.println();
        int V = 5;
        int edges[][] = {{0,1},{1,2},{2,0},{0,3},{3,4}};
        int g[][] = new int[V][V];
        for(int edge[] : edges){
            g[edge[0]][edge[1]] = 1;
        }
        ArrayList<ArrayList<Integer>> graph = makeGraph(V,g);
        System.out.println("Graph is ");
        System.out.println(graph);

        new TarzansBridgeFindingAlgorithm().findBridges(V,graph);
    }

    static void test2(){
        System.out.println();
        int V = 7;
        int edges[][] = {{0,1},{1,2},{0,2},{1,6},{1,3},{3,5},{5,4},{4,1}};
        int g[][] = new int[V][V];
        for(int edge[] : edges){
            g[edge[0]][edge[1]] = 1;
        }
        ArrayList<ArrayList<Integer>> graph = makeGraph(V,g);
        System.out.println("Graph is ");
        System.out.println(graph);

        new TarzansBridgeFindingAlgorithm().findBridges(V,graph);
    }

    static void test3(){
        System.out.println();
        int V = 4;
        int edges[][] = {{0,1},{1,2},{2,3}};
        int g[][] = new int[V][V];
        for(int edge[] : edges){
            g[edge[0]][edge[1]] = 1;
        }
        ArrayList<ArrayList<Integer>> graph = makeGraph(V,g);
        System.out.println("Graph is ");
        System.out.println(graph);

        new TarzansBridgeFindingAlgorithm().findBridges(V,graph);
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}