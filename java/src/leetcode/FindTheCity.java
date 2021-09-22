//https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
//Floyd warshall, all source shortest path algo
package src.leetcode;

import java.util.Arrays;

public class FindTheCity {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int MAX = Integer.MAX_VALUE;
        int graph[][] = new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(graph[i],MAX);
        }
        for(int e[] : edges){
            graph[e[0]][e[1]] = e[2];
            graph[e[1]][e[0]] = e[2];
        }

        int dist[][] = new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(dist[i],MAX);
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dist[i][j] = graph[i][j];
            }
        }
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(dist[i][k]!=MAX&&dist[k][j]!=MAX&&dist[i][j]>dist[i][k]+dist[k][j]){
                        dist[i][j] = dist[i][k]+dist[k][j];
                    }
                }
            }
        }
        int ans = 0,finalT=MAX;
        for(int i=0;i<n;i++){
            int t=0;
            for(int j=0;j<graph[i].length;j++){
                if(i!=j&&dist[i][j]<=distanceThreshold){
                    t++;
                }
            }
            if(t<=finalT){
                ans = i;
                finalT = t;
            }
        }
        return ans;
    }
}
