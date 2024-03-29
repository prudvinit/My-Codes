package src;//https://practice.geeksforgeeks.org/problems/topological-sort/1#
import java.util.*;
import java.io.*;
import java.lang.*;

public class TopologicalSort {
    public static void main(String[] args) throws IOException {
        BufferedReader read =
                new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            String st[] = read.readLine().trim().split("\\s+");
            int edg = Integer.parseInt(st[0]);
            int nov = Integer.parseInt(st[1]);

            for (int i = 0; i < nov + 1; i++)
                list.add(i, new ArrayList<Integer>());

            int p = 0;
            for (int i = 1; i <= edg; i++) {
                String s[] = read.readLine().trim().split("\\s+");
                int u = Integer.parseInt(s[0]);
                int v = Integer.parseInt(s[1]);
                list.get(u).add(v);
            }

            int[] res = new Solution().topoSort(nov, list);

            if (check(list, nov, res) == true)
                System.out.println("1");
            else
                System.out.println("0");
        }
    }
    static boolean check(ArrayList<ArrayList<Integer>> list, int V, int[] res) {

        if(V!=res.length)
            return false;

        int[] map = new int[V];
        for (int i = 0; i < V; i++) {
            map[res[i]] = i;
        }
        for (int i = 0; i < V; i++) {
            for (int v : list.get(i)) {
                if (map[i] > map[v]) return false;
            }
        }
        return true;
    }
}
// } Driver Code Ends


/*Complete the function below*/


class Solution
{
    static void dfs(int v, ArrayList<ArrayList<Integer>> adj,Set<Integer> visited, Stack<Integer> explored){
        if(v>=adj.size()){
            return;
        }
        visited.add(v);
        for(int child : adj.get(v)){
            if(!visited.contains(child)){
                dfs(child,adj,visited,explored);
            }
        }
        //Add the vertex to stack , if all the children of the nodes are explored
        explored.add(v);
    }

    //Function to return list containing vertices in Topological order.
    static int[] topoSortDFS(int V, ArrayList<ArrayList<Integer>> adj)
    {
        System.out.println(adj);
        // add your code here
        Stack<Integer> explored = new Stack<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        for(int i=0;i<adj.size();i++){
            if(!visited.contains(i))
                dfs(V,adj,visited,explored);
        }
        //Once the DFS is complete, convert the 'explored' stack to array
        int ans[] = new int[explored.size()];
        int ind = 0;
        for(int x : explored){
            ans[ind++] = x;
        }
        return ans;
    }

    //Function to return list containing vertices in Topological order.
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj)
    {
        int indegree[] = new int[V];
        for(ArrayList<Integer> v : adj){
            for(Integer c : v){
                indegree[c]++;
            }
        }
        Queue<Integer> q = new LinkedList();
        for(int i=0;i<V;i++){
            if(indegree[i]==0){
                q.add(i);
            }
        }
        List<Integer> top = new ArrayList();
        int c = 0;
        while (!q.isEmpty()){
            Integer node = q.poll();
            top.add(node);
            c++;
            for(int child : adj.get(node)){
                indegree[child]--;
                if(indegree[child]==0){
                    q.add(child);
                }
            }
        }
        if(c==V){
            int ans[] = new int[top.size()];
            for(int i=0;i<top.size();i++){
                ans[i] = top.get(i);
            }
            return ans;
        }
//        System.out.println("Cycle exists");
        return null;
    }

    static ArrayList<ArrayList<Integer>> graph(int edges[][], int v){
        ArrayList<ArrayList<Integer>> graph = new ArrayList();
        for(int i=0;i<v;i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int e[] : edges){
            graph.get(e[0]).add(e[1]);
        }
        return graph;
    }
    static void test1(){
        int edges[][] = {{3,0},{1,0},{2,0}};
        int V = 4;
        System.out.println(Arrays.toString(topoSort(V,graph(edges,V))));
    }

    static void test2(){
        int edges[][] = {{5,2},{5,0},{4,0},{4,1},{2,3},{3,1}};
        int V = 6;
        System.out.println(Arrays.toString(topoSort(V,graph(edges,V))));
    }

    public static void main(String[] args) {
//        test1();
        test2();
    }
}
