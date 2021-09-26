package src.leetcode;

import java.util.Arrays;

public class MinimumCostToReachDestinationInTime {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = 1000,MAX=Integer.MAX_VALUE;
        int graph[][] = new int[n+1][n+1];
        for(int i=0;i<graph.length;i++){
            Arrays.fill(graph[i],MAX);
        }
        for(int edge[] : edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph[u][v] = w;
            graph[v][u] = w;
        }
        return -1;
    }
}
