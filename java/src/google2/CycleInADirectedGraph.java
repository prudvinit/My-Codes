package src.google2;

import java.util.ArrayList;

public class CycleInADirectedGraph {

    private boolean hasCycle(int V, boolean visited[], boolean explored[], ArrayList<ArrayList<Integer>> graph){
        visited[V] = true;
        boolean result = false;
        for(Integer child : graph.get(V)){
            if(visited[child]&&!explored[child]){
                return true;
            }
            if(!visited[child])
            result =result || hasCycle(child,visited,explored,graph);
        }
        explored[V] = true;
        return result;
    }


    // Function to detect cycle in a directed graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        boolean visited[] = new boolean[V+1];
        boolean explored[] = new boolean[V+1];
        boolean hasCycle = false;
        for(int i=0;i<V;i++){
            hasCycle = hasCycle || hasCycle(i,visited,explored,adj);
        }
        return hasCycle;
    }

    public static void main(String[] args) {

    }
}
