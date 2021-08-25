package src;

import java.util.ArrayList;
import java.util.Arrays;


class ArticulationPoints{
    static int time = 1;
    public static void dfs(ArrayList<Integer> graph[], int node, int parent, boolean visited[],int disc[], int low[], int children[], boolean articulation[]){
        System.out.println("DFS "+node);
        for(int child : graph[node]){
            if(!visited[child] && child!=parent){
                children[node]++;
                visited[child] = true;
                disc[child] = low[child] = time++;
                dfs(graph,child,node,visited,disc,low,children,articulation);
                //Once the child is traversed, we can check if the node is an articulation point for the child
                if( disc[node]<=low[child]){
                    articulation[node] = true;
                }
                //We can see if there is a bridge between node and child
                if( disc[node]<low[child]){
                    System.out.println("Bridge "+node+" -> "+child+"");
                }
            }
            if(child!=parent) {
                low[node] = Math.min(low[node], low[child]);
            }
        }
    }
    public static void main(String[] args) {
        ArrayList<Integer> graph[] = new ArrayList[9];
        for(int i=0;i<9;i++){
            graph[i] = new ArrayList<Integer>();
        }
        //Edges defining the graph
        int edge[][] = {{1,2},{1,3},{2,3},{3,4},{4,5},{5,6},{5,7},{6,7},{6,8}};
        //prepare the graph
        for(int i=0;i<edge.length;i++){
            graph[edge[i][0] ].add(edge[i][1]);
            graph[edge[i][1] ].add(edge[i][0]);
        }
        System.out.println(Arrays.toString(graph  ));
        boolean visited[] = new boolean[graph.length];
        int disc[] = new int[graph.length];
        int low[] = new int[graph.length];
        int children[] = new int[graph.length];
        boolean articulation[] = new boolean[graph.length];
        Arrays.fill(disc,Integer.MAX_VALUE);
        Arrays.fill(low,Integer.MAX_VALUE);

        int root = 4;
        visited[root] = true;
        disc[root] = time;
        low[root] = time;
        time++;

        dfs(graph,root,-1,visited,disc,low,children,articulation);
        articulation[root] = children[root]>1;
        for(int i=0;i<graph.length;i++){
            if(articulation[i])
            System.out.print(i+" ");
        }
    }
}
