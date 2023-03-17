package src.google2;

import java.util.ArrayList;

public class CycleInUndirectedGraph {

    private boolean hasCycle(int V, int parent, boolean visited[], ArrayList<ArrayList<Integer>> graph){
        visited[V] = true;
        boolean result = false;
        for(Integer child : graph.get(V)){
            if(child!=parent&&visited[child]){
                return true;
            }
            if(!visited[child]) {
                result = result || hasCycle(child, V, visited, graph);
            }
        }
        return result;
    }

    // Function to detect cycle in a directed graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        boolean visited[] = new boolean[V+1];
        boolean hasCycle = false;
        for(int i=0;i<V;i++){
            if(!visited[i])
                hasCycle = hasCycle || hasCycle(i,-1,visited,adj);
        }
        return hasCycle;
    }

    public static void main(String[] args) {

    }
}
