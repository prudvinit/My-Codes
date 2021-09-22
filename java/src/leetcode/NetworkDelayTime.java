package src.leetcode;

import java.util.Arrays;

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        int dist[] = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[k] = 0;
        for(int i=0;i<times.length-1;i++){
            for(int edge[] : times){
                int u = edge[0],v = edge[1],w=edge[2];
                if(dist[u]!= Integer.MAX_VALUE&&dist[v]>dist[u]+w){
                    dist[v] = dist[u]+w;
                    System.out.println("v updated "+dist[v]);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<dist.length;i++){
            ans = Math.max(ans,dist[i]);
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }
}
