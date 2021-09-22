package src.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PossibleBipartition {
    //Convert the edges into adjacecy list
    ArrayList<Integer>[] graph(int arr[][], int n){
        ArrayList<Integer> ans[] = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            ans[i] = new ArrayList();
        }
        for(int e[] : arr){
            ans[e[0]].add(e[1]);
            ans[e[1]].add(e[0]);
        }
        return ans;

    }
    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<Integer> graph[] = graph(dislikes,n);
        int colors[] = new int[n+1];
        Arrays.fill(colors,-1);
        for(int i=1;i<=n;i++){
            //IF already visited, don't BFS
            if(colors[i]!=-1){
                continue;
            }
            //Perform BFS
            Queue<Integer> q = new LinkedList();
            q.add(i);
            //Assign color 1
            colors[i]=1;
            while(!q.isEmpty()){
                int e = q.remove();
                for(int c : graph[e]){
                    //If child not colored, perform BFS
                    if(colors[c]==-1){
                        q.add(c);
                        colors[c]=1-colors[e];
                    }
                    //If child aready colored, validate with parent color
                    else if(colors[c]==colors[e]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}