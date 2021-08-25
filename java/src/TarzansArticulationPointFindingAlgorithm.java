package src;

import java.util.ArrayList;
import java.util.Arrays;

public class TarzansArticulationPointFindingAlgorithm {

    public void runDFS(int v, int p, ArrayList<ArrayList<Integer>> adj, boolean visited[], int disc[], int low[], int timer) {
        //Update the visited. low and discovery times
        visited[v] = true;
        low[v] = timer;
        disc[v] = timer;
        int childCount = 0;
        //Iterate through all the children
        for(int child : adj.get(v)){
            //Do not process the parent
            if(child!=p){
                //Check if the child is visited
                if(!visited[child]){
                    //Increment the count
                    childCount++;
                    //Call DFS on child with increased timer
                    runDFS(child,v,adj,visited,disc,low,timer+1);
                }
                //Update the low for parent based on child node
                low[v] = Math.min(low[v],low[child]);
                //If this is root and there are more children, it is an articulation point
                if(p==-1&&childCount>1){
                    System.out.println("Articulation Point "+v);
                    break;
                }
                //If this is not the root and there is no child node which is reachable to the ancestor of the node, this is an articulation point
                else if(p!=-1&&low[child]>low[v]){
                    System.out.println("Articulation Point "+v);
                    break;
                }
            }
        }
    }

    public void findingArticulationPoints(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean visited[] = new boolean[V];
        int disc[] = new int[V];
        int low[] = new int[V];
        Arrays.fill(low, Integer.MAX_VALUE);
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                runDFS(i, -1, adj, visited, disc, low, 0);
            }
        }
    }

    static ArrayList<ArrayList<Integer>> makeGraph(int V, int g[][]) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (g[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        return graph;
    }

    static void test1() {
        System.out.println();
        int V = 5;
        int edges[][] = {{0, 1}, {1, 2}, {2, 0}, {0, 3}, {3, 4}};
        int g[][] = new int[V][V];
        for (int edge[] : edges) {
            g[edge[0]][edge[1]] = 1;
        }
        ArrayList<ArrayList<Integer>> graph = makeGraph(V, g);
        System.out.println("Graph is ");
        System.out.println(graph);

        new TarzansArticulationPointFindingAlgorithm().findingArticulationPoints(V, graph);
    }

    static void test2() {
        System.out.println();
        int V = 4;
        int edges[][] = {{0, 1}, {1, 2}, {2, 3}};
        int g[][] = new int[V][V];
        for (int edge[] : edges) {
            g[edge[0]][edge[1]] = 1;
        }
        ArrayList<ArrayList<Integer>> graph = makeGraph(V, g);
        System.out.println("Graph is ");
        System.out.println(graph);

        new TarzansArticulationPointFindingAlgorithm().findingArticulationPoints(V, graph);
    }

    static void test3() {
        System.out.println();
        int V = 7;
        int edges[][] = {{0, 1}, {1, 2}, {0, 2}, {1, 6}, {1, 3}, {3, 5}, {1, 4}, {4, 5}};
        int g[][] = new int[V][V];
        for (int edge[] : edges) {
            g[edge[0]][edge[1]] = 1;
        }
        ArrayList<ArrayList<Integer>> graph = makeGraph(V, g);
        System.out.println("Graph is ");
        System.out.println(graph);

        new TarzansArticulationPointFindingAlgorithm().findingArticulationPoints(V, graph);
    }

    static void test4() {
        System.out.println();
        int V = 6;
        int edges[][] = {{0, 1}, {1, 2}, {0, 2}, {1, 5}, {1, 3}, {3, 4}, {1, 4}};
        int g[][] = new int[V][V];
        for (int edge[] : edges) {
            g[edge[0]][edge[1]] = 1;
        }
        ArrayList<ArrayList<Integer>> graph = makeGraph(V, g);
        System.out.println("Graph is ");
        System.out.println(graph);

        new TarzansArticulationPointFindingAlgorithm().findingArticulationPoints(V, graph);
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }
}