//https://practice.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1

import java.util.*;
import java.io.*;
import java.lang.*;

public class KosarajusStronglyConnectedComponents
{
    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-- > 0)
        {
            // arraylist of arraylist to represent graph
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

            int V = Integer.parseInt(sc.next());
            int E = Integer.parseInt(sc.next());

            for(int i =0; i < V; i++)
                adj.add(i, new ArrayList<Integer>());

            for(int i = 1; i <= E; i++)
            {    int u = Integer.parseInt(sc.next());
                int v = Integer.parseInt(sc.next());

                // adding directed edgese between
                // vertex 'u' and 'v'
                adj.get(u).add(v);
            }

            Solution ob = new Solution();
            System.out.println(ob.kosaraju(V, adj));
        }
    }
}
// } Driver Code Ends


//User function Template for Java


class Solution
{

    void dfs(int v, ArrayList<ArrayList<Integer>> adj, Set<Integer> visited, Stack<Integer> finishTimes){
        visited.add(v);
        for(int child : adj.get(v)){
            if(!visited.contains(child)){
                dfs(child,adj,visited,finishTimes);
            }
        }
        finishTimes.add(v);
    }

    //Function to find number of strongly connected components in the graph.
    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj)
    {
        //code here
        Stack<Integer> finishTimes = new Stack<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        ArrayList<ArrayList<Integer>> rev = new ArrayList();
        //Perform DFS to find the finish times. The node to finish last will be at the top of the stack
        for(int i=0;i<V;i++){
            rev.add(new ArrayList<Integer>());
            if(!visited.contains(i)){
                dfs(i,adj,visited,finishTimes);
            }
        }
        //Reverse the graph
        for(int i=0;i<V;i++){
            for(int j=0;j<adj.get(i).size();j++){
                int u = i;
                int v = adj.get(i).get(j);
                rev.get(v).add(u);
            }
        }
        visited = new HashSet();
        int ans = 0;
        //Perform dfs in the reverse order of finish times
        while(!finishTimes.isEmpty()){
            int v = finishTimes.pop();
            System.out.println("Finish "+v);
            if(!visited.contains(v)){
                ans++;
                dfs(v,rev,visited, new Stack<Integer>());
            }
        }
        return ans;
    }
}
