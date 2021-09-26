package src.leetcode;

import java.util.*;

public class ReachableNodesInSubgraph {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        ArrayList<int[]> graph[] = new ArrayList[n];
        for(int i=0;i<n;i++){
            graph[i] = new ArrayList();
        }
        for(int[] e : edges){
            graph[e[0]].add(new int[]{e[1],e[2]});
            graph[e[1]].add(new int[]{e[0],e[2]});
        }
        int dist[] = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return dist[o1]-dist[o2];
            }
        });

        pq.add(0);
        boolean visited[] = new boolean[n];
        int par[] = new int[n];
        par[0] = -1;
        while (!pq.isEmpty()){
            int node = pq.remove();
            if(visited[node]){
                continue;
            }
            visited[node] = true;
            for(int child[] : graph[node]){
                if(!visited[child[0]]) {
                    if (dist[child[0]] > dist[node] + child[1] + 1) {
                        dist[child[0]] = dist[node] + child[1] + 1;
                        pq.add(child[0]);
                        par[child[0]] = node;
                    }
                }
            }
        }
        int ans = 0;
        for(int i=0;i<n;i++){
            if(dist[i]<=maxMoves)ans++;
        }
        for(int e[] : edges){
            int u = e[0];
            int v = e[1];
            int w = e[2];
            int eu = Math.max(maxMoves-dist[u],0);
            int ev = Math.max(maxMoves-dist[v],0);
            ans = ans+Math.min(eu+ev,w);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new ReachableNodesInSubgraph().reachableNodes(new int[][]{{0,1,10},{0,2,1},{1,2,2}},6,3));
    }
}
