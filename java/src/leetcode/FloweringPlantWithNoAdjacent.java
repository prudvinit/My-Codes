//https://leetcode.com/problems/flower-planting-with-no-adjacent/
package src.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class FloweringPlantWithNoAdjacent {
    private boolean isValid(ArrayList<Integer> graph[], int colors[]){
        for(int i=1;i<graph.length;i++){
            for(int c : graph[i]){
                if(colors[i]==colors[c])return false;
            }
        }
        return true;
    }

    private boolean dfs(ArrayList<Integer> graph[], int v, int colors[]){
        if(v==graph.length){
            if(isValid(graph,colors)){
                return true;
            }
            return false;
        }
        for(int c=1;c<=4;c++){
            boolean possible = true;
            for(int child : graph[v]){
                if(colors[child]==c){
                    possible = false;
                    break;
                }
            }
            if(possible){
                colors[v] = c;
                boolean tans = dfs(graph,v+1,colors);
                if(tans)return true;
                colors[v] = 0;
            }
        }
        return false;
    }
    public int[] gardenNoAdj(int n, int[][] paths) {
        int colors[] = new int[n+1];
        ArrayList<Integer> graph[] = new ArrayList[n+1];
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList();
        }
        for(int[] path : paths){
            graph[path[0]].add(path[1]);
            graph[path[1]].add(path[0]);
        }
        dfs(graph,1,colors);
        return Arrays.copyOfRange(colors,1,colors.length);
    }
}
