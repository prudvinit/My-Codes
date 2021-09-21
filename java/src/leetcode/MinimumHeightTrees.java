package src.leetcode;

import java.util.*;

public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //Convert into adjacency list tree
        ArrayList<Integer> tree[] = new ArrayList[n];
        int indegree[] = new int[n];
        for(int i=0;i<n;i++){
            tree[i] = new ArrayList<>();
        }
        //Count indegree
        for(int edge[] : edges){
            indegree[edge[0]]++;
            indegree[edge[1]]++;
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> fans = new ArrayList();
        //Add all nodes having indegree <=0. i.e leaf nodes
        for(int i=0;i<n;i++){
            if(indegree[i]<=1){
                queue.add(i);
                fans.add(i);
            }
        }
        boolean visited[] = new boolean[n];
        while (!queue.isEmpty()){
            List<Integer> tempAns = new ArrayList<>();
            Queue<Integer> temp = new LinkedList();
            while (!queue.isEmpty()){
                int e = queue.remove();
                tempAns.add(e);
                visited[e] = true;
                //Reduce indegree for all the children.
                for(int child : tree[e]){
                    indegree[child]--;
                    //If indegree is 1, add it to queue.
                    if(indegree[child]==1&&!visited[child]){
                        temp.add(child);
                    }
                }
            }
            queue = temp;
            //If no more nodes found with indgree 1, this is our answer
            if(temp.isEmpty()){
                fans = tempAns;
            }
        }
        return fans;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumHeightTrees().findMinHeightTrees(4,new int[][]{{1,0},{1,2},{1,3}}));
        System.out.println(new MinimumHeightTrees().findMinHeightTrees(6, new int[][]{{3,0},{3,1},{3,2},{3,4},{5,4}}));
        System.out.println(new MinimumHeightTrees().findMinHeightTrees(1, new int[][]{}));
        System.out.println(new MinimumHeightTrees().findMinHeightTrees(2, new int[][]{{0,1}}));
    }

}
