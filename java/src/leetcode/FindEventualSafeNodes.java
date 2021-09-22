//https://leetcode.com/problems/find-eventual-safe-states/
package src.leetcode;

import java.util.*;

public class FindEventualSafeNodes {

    List<Integer> ans = new ArrayList();
    boolean dfs(int graph[][], int node, int visited[]){
        //mark it as visited once
        visited[node] = 1;
        for(int c : graph[node]){
            //If child is already visited, it forms a cycle, we can return true.
            //Or if the child is not completely explored, we can recursively check for cycle
            if(visited[c]==1||(visited[c]!=2&&dfs(graph,c,visited))){
                return true;
            }
        }
        //Mark it as completly explored
        visited[node] = 2;
        //Once completely explored means, it can't be part of cycle
        ans.add(node);
        return false;
    }
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int visited[] = new int[graph.length];
        for(int i=0;i<visited.length;i++){
            if(visited[i]==0){
                dfs(graph,i,visited);
            }
        }
        Collections.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new FindEventualSafeNodes().eventualSafeNodes(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}}));
        System.out.println(new FindEventualSafeNodes().eventualSafeNodes(new int[][]{{1,2,3,4},{1,2},{3,4},{0,4},{}}));
    }
}
