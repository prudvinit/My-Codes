package src.leetcode;

import java.util.Arrays;

public class CheapestFlightWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int s) {
        int MAX = Integer.MAX_VALUE;
        int graph[][] = new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(graph[i],MAX);
        }
        //Populate the graph
        for(int flight[] : flights){
            graph[flight[0]][flight[1]] = flight[2];
        }
        int ans = graph[src][dst];
        int dist[][] = new int[n][n];
        //Initialize dist
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dist[i][j] = graph[i][j];
            }
        }
        //Multiply matrix s times
        for(int stop=1;stop<=s;stop++){
            int tr[][] = new int[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    int tans = Integer.MAX_VALUE;
                    for(int k=0;k<n;k++){
                        if(dist[i][k]!=MAX && graph[k][j]!=MAX){
                            tans = Math.min(tans,dist[i][k]+graph[k][j]);
                        }
                    }
                    tr[i][j] = tans;
                }
            }
            //Update the result
            dist = tr;
            //Update the ans
            ans = Math.min(ans,dist[src][dst]);
        }
        return ans==MAX?-1:ans;
    }
}
