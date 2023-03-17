package src.google2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TopologicalSort {

    private static void dfs(int V, boolean visited[], ArrayList<ArrayList<Integer>> adj, Stack<Integer> top){
        visited[V] = true;
        for(int child : adj.get(V)){
            if(!visited[child]){
                dfs(child,visited,adj,top);
            }
        }
        top.add(V);
    }

    //Function to return list containing vertices in Topological order.
    static int[] topoSort2(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // add your code here
        boolean visited[] = new boolean[V];
        Stack<Integer> stack = new Stack();
        int result[] = new int[V];
        for(int i=0;i<V;i++){
            if(!visited[i]){
                dfs(i,visited,adj,stack);
            }
        }
        for(int i=0;i<V;i++){
            result[i] = stack.pop();
        }
        return result;
    }

    //Kahns topological sort
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj)
    {
        int ind[] = new int[V];
        for(int i=0;i<adj.size();i++){
            for(int j=0;j<adj.get(i).size();j++){
                ind[adj.get(i).get(j)]++;
            }
        }
        Queue<Integer> queue = new LinkedList();
        for(int i=0;i<V;i++){
            if(ind[i]==0)queue.add(i);
        }
        int ans[] = new int[V];
        int index=0;
        while (!queue.isEmpty()){
            int e = queue.remove();
            ans[index++] = e;
            for(int child : adj.get(e)){
                ind[child]--;
                if(ind[child]==0)queue.add(child);
            }
        }
        return ans;
    }
}
