package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class TarzansStronglyConnectedComponents{

    public void run(int v, ArrayList<ArrayList<Integer>> adj, boolean visited[], int disc[], int low[], Stack<Integer> stack, int time){
        //Mark it as visited, update the discovery time and low time
        visited[v] = true;
        disc[v] = time;
        low[v] = time;
        //Push it to stack
        stack.push(v);
        //Loop through all children
        for(int child : adj.get(v)){
            //If not visited, do dfs on child with updated time
            if(!visited[child]){
                run(child,adj,visited,disc,low,stack,time+1);
            }
            //Once the child is explored, update the parent's low, only if the child is on the stack
            if(stack.contains(child)) {
                low[v] = Math.min(low[v], low[child]);
            }
        }
        //Once all children are explored, check if discovery and low are the same.
        if(disc[v]==low[v]){
            //Keep popping from the stack until the element is found. These are all the elements of the connected component
            ArrayList<Integer> list = new ArrayList();
            while (!stack.isEmpty()&&stack.peek()!=v){
                list.add(stack.pop());
            }
            if(!stack.isEmpty()){
                list.add(stack.pop());
            }
            System.out.println(list);
        }
    }
    public void findStronglyConnectedComponents(int V, ArrayList<ArrayList<Integer>> adj)
    {
        boolean visited[] = new boolean[V];
        int disc[] = new int[V];
        int low[] = new int[V];
        Arrays.fill(low,Integer.MAX_VALUE);
        Stack<Integer> stack = new Stack();
        System.out.println("Strongly Connected Components are ");
        for(int i=0;i<V;i++) {
            if(!visited[i]) {
                run(i, adj, visited, disc, low, stack, 0);
            }
        }
        return;
    }

    static ArrayList<ArrayList<Integer>> makeGraph(int V, int g[][]){
        ArrayList<ArrayList<Integer>> graph = new ArrayList();
        for(int i=0;i<V;i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                if(g[i][j]==1)
                    graph.get(i).add(j);
            }
        }
        return graph;
    }

    static void test1(){
        int V = 8;
        int edges[][] = {{0,1},{1,2},{2,0},{6,2},{6,0},{6,4},{4,5},{5,6},{5,0},{3,4},{4,5},{7,5},{3,7},{7,3}};
        int g[][] = new int[V][V];
        for(int edge[] : edges){
            g[edge[0]][edge[1]] = 1;
        }
        ArrayList<ArrayList<Integer>> graph = makeGraph(V,g);
        System.out.println("Graph is ");
        System.out.println(graph);

        new TarzansStronglyConnectedComponents().findStronglyConnectedComponents(V,graph);
    }

    static void test2(){
        int V = 7;
        int edges[][] = {{0,1},{1,6},{6,0},{1,4},{1,2},{6,2},{4,5},{5,4},{3,4},{3,5},{2,3},{3,2}};
        int g[][] = new int[V][V];
        for(int edge[] : edges){
            g[edge[0]][edge[1]] = 1;
        }
        ArrayList<ArrayList<Integer>> graph = makeGraph(V,g);
        System.out.println("Graph is ");
        System.out.println(graph);

        new TarzansStronglyConnectedComponents().findStronglyConnectedComponents(V,graph);
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}