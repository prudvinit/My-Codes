package src.google2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DijkstrasAlgorithm {
    //Function to find the shortest distance of all the vertices
    //from the source vertex S.
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
        // Write your code here
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        pq.add(new Integer[]{S,0});
        int ans[] = new int[V];
        boolean visited[] = new boolean[V];
        Arrays.fill(ans,Integer.MAX_VALUE);
        ans[S] = 0;
        while (!pq.isEmpty()){
            Integer top[] = pq.remove();
            int u = top[0];
            if(visited[u])continue;
            visited[u] = true;
            int dist = top[1];
            for(ArrayList<Integer> child : adj.get(u)){
                int v = child.get(0);
                int w = child.get(1);
                if(ans[u]+w<ans[v]){
                    ans[v] = ans[u]+w;
                    pq.add(new Integer[]{v,ans[v]});
                }
            }
        }
        return ans;
    }

}
