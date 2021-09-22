package src.leetcode;

import java.util.*;

public class ShortestPathWithAlternatingColors {
    public int[] shortestAlternatingPaths2(int n, int[][] red_edges, int[][] blue_edges) {
        List<int[]> graph[] = new ArrayList[n];
        final int MAX = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            graph[i] = new ArrayList();
        }
        for (int edge[] : red_edges) {
            graph[edge[0]].add(new int[]{edge[1],1});
        }
        for (int edge[] : blue_edges) {
            graph[edge[0]].add(new int[]{edge[1],2});
        }
//        for(int i=0;i<graph.length;i++){
//            System.out.println("For node "+i);
//            for(int[] c : graph[i])
//            System.out.println(Arrays.toString(c));
//        }
        int distRed[] = new int[n];
        int distBlue[] = new int[n];
        int dist[] = new int[n];
        boolean visited[] = new boolean[n];
        Arrays.fill(distBlue, MAX);
        Arrays.fill(distRed, MAX);
        Arrays.fill(dist, MAX);
        distBlue[0] = distRed[0] = dist[0] = 0;
        Queue<Integer> queue = new LinkedList();
//        queue.add(0);
        visited[0] = true;
        int col[] = new int[n];
        for(int c[] : graph[0]){
            queue.add(c[0]);
            dist[c[0]]=1;
            col[c[0]] = c[1];
        }

        while (!queue.isEmpty()) {
            int e = queue.remove();
//            System.out.println("Node is "+e+" Parent is "+col[e]+" Childs  ");
//            for(int[] c : graph[e])
//                System.out.println(Arrays.toString(c));
            visited[e] = true;
            for (int i = 0; i < graph[e].size(); i++) {
                int c[] = graph[e].get(i);
//                System.out.println("Child is "+Arrays.toString(c));
                if ( (c[1] != col[e]) ) {
//                    System.out.println("Child found "+c[0]);
                    col[i] = c[1];
                    if(dist[e]!=MAX) {
                        dist[c[0]] = Math.min(dist[i=c[0]], 1 + dist[e]);
                    }
//                    System.out.println("Dist updated "+dist[c[0]]);
                    if(!visited[c[0]]){
                        queue.add(c[0]);
                    }
                }
            }
        }
        for(int i=0;i<dist.length;i++){
            dist[i] = (dist[i]==MAX?-1:dist[i]);
        }
        System.out.println("DONE");
        return dist;
    }

    public void dfs(List<int[]> graph[], int v, int parent, int parentCol){
        for(int[] child : graph[v]){
            int node = child[0];
            int color = child[1];
//            if()
        }
    }
    
    void add(Map<String,Integer> map,int from, int to, int color){
        map.put(from+" "+to+" "+color,map.getOrDefault(from+" "+to+" "+color,0)+1);
    }

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        List<int[]> graph[] = new ArrayList[n];
        final int MAX = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            graph[i] = new ArrayList();

        }
        Map<String,Integer> visited = new HashMap();
        for (int edge[] : red_edges) {
            graph[edge[0]].add(new int[]{edge[1],1});
            add(visited,edge[0],edge[1],1);
//            graph[edge[1]].add(new int[]{edge[0],1});
        }
        for (int edge[] : blue_edges) {
            graph[edge[0]].add(new int[]{edge[1],2});
            add(visited,edge[0],edge[1],2);
//            graph[edge[1]].add(new int[]{edge[0],2});
        }
//        for(int i=0;i<graph.length;i++){
//            System.out.println("For node "+i);
//            for(int[] c : graph[i])
//            System.out.println(Arrays.toString(c));
//        }
        int distRed[] = new int[n];
        int distBlue[] = new int[n];
        int dist[] = new int[n];

        boolean vis[] = new boolean[n];
        Arrays.fill(dist, MAX);
        distBlue[0] = distRed[0] = dist[0] = 0;
        Queue<Integer> queue = new LinkedList();
//        queue.add(0);
        vis[0] = true;
        int col[] = new int[n];
        int parent[] = new int[n];
        for(int c[] : graph[0]){
            add(visited,0,c[0],c[1]);
            queue.add(c[0]);
            dist[c[0]]=1;
            col[c[0]] = c[1];
            parent[c[0]] = 0;
        }

        while (!queue.isEmpty()) {
            int e = queue.remove();
            vis[e] = true;
            System.out.println("Node is "+e+" "+col[e]+"\nChilds  ");
            for(int[] c : graph[e])
                System.out.print(Arrays.toString(c)+" ");
            System.out.println();
            add(visited,parent[e],e,col[e]);
//            visited[e] = true;
            for(int c[] : graph[e]){
//            while(!graph[e].isEmpty()){
//            for (int i = 0; i < graph[e].size(); i++) {
//                int c[] = graph[e].get(graph[e].size()-1);

                if ( (c[1] != col[e]) && c[0]!=parent[0] && visited.getOrDefault(e+" "+c[0]+" "+c[1],0)>0&&!vis[c[0]]) {
//                    graph[e].remove(graph[e].size()-1);
                    System.out.println("Child is "+Arrays.toString(c));
//                    System.out.println("Child found "+c[0]);
                    col[c[0]] = c[1];
                    parent[c[0]] = e;
                    if(dist[e]!=MAX&&!vis[c[0]]) {
                        dist[c[0]] = Math.min(dist[c[0]], 1 + dist[e]);
                    }
                    System.out.println("Dist updated "+dist[c[0]]);
//                    if(!visited[c[0]]){
                        queue.add(c[0]);
                    add(visited,e,c[0],c[1]-1);
//                    }
                }
            }
        }
        for(int i=0;i<dist.length;i++){
            dist[i] = (dist[i]==MAX?-1:dist[i]);
        }
        System.out.println("DONE");
        return dist;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new ShortestPathWithAlternatingColors().shortestAlternatingPaths(3, new int[][]{{0,1},{1,2}}, new int[][]{})));
//        System.out.println(Arrays.toString(new ShortestPathWithAlternatingColors().shortestAlternatingPaths(3, new int[][]{{0,1},{2,1}}, new int[][]{})));
//        System.out.println(Arrays.toString(new ShortestPathWithAlternatingColors().shortestAlternatingPaths(3, new int[][]{{1,0},{2,1}}, new int[][]{})));
//        System.out.println(Arrays.toString(new ShortestPathWithAlternatingColors().shortestAlternatingPaths(3, new int[][]{{0,1}}, new int[][]{{1,2}})));
//        System.out.println(Arrays.toString(new ShortestPathWithAlternatingColors().shortestAlternatingPaths(3, new int[][]{{0,1},{0,2}}, new int[][]{{1,0}})));
        System.out.println(Arrays.toString(new ShortestPathWithAlternatingColors().shortestAlternatingPaths(5, new int[][]{{0,1},{1,2},{2,3},{3,4}}, new int[][]{{1,2},{2,3},{3,1}})));
    }
}
