//https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/
package src.leetcode;

import javax.swing.*;
import java.util.*;

public class NumberOfRestrictedPathsFromFirstNodeToLastNode {

    static final int MOD = 1000000007;

    public long way(int v, List<int[]> graph[], int dist[], long dp[]) {
        if (v == graph.length - 1) {
            return 1l;
        }
        if (dp[v] != -1) {
            return dp[v];
        }
        long ways = 0;
        for (int child[] : graph[v]) {
            if (dist[child[0]] < dist[v]) {
                ways = (ways + way(child[0], graph, dist, dp)) % MOD;
            }
        }
        return dp[v] = ways;
    }


    public int countRestrictedPaths(int n, int[][] edges) {
        List<int[]> graph[] = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList();
        }
        for (int edge[] : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        int dist[] = new int[n + 1];
        boolean visited[] = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{n, 0});
        while (!pq.isEmpty()) {
            int node[] = pq.remove();
            if (!visited[node[0]]) {
                visited[node[0]] = true;
                for (int child[] : graph[node[0]]) {
                    if (!visited[child[0]]) {
                        if (dist[child[0]] > dist[node[0]] + child[1]) {
                            dist[child[0]] = dist[node[0]] + child[1];
                            pq.add(new int[]{child[0], dist[child[0]]});
                        }
                    }
                }
            }
        }
        // System.out.println(Arrays.toString(dist));
        long dp[] = new long[n + 1];
        Arrays.fill(dp, -1);
        long ans = way(1, graph, dist, dp);
        // System.out.println(Arrays.toString(dist));
        return (int) ans;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfRestrictedPathsFromFirstNodeToLastNode().countRestrictedPaths(5, new int[][]{{1, 2, 3}, {1, 3, 3}, {2, 3, 1}, {1, 4, 2}, {5, 2, 2}, {3, 5, 1}, {5, 4, 10}}));
        System.out.println(new NumberOfRestrictedPathsFromFirstNodeToLastNode().countRestrictedPaths(7, new int[][]{{1, 3, 1}, {4, 1, 2}, {7, 3, 4}, {2, 5, 3}, {5, 6, 1}, {6, 7, 2}, {7, 5, 3}, {2, 6, 4}}));
    }

}
