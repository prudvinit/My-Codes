package src;

// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            String[] S = br.readLine().trim().split(" ");
            int V = Integer.parseInt(S[0]);
            int E = Integer.parseInt(S[1]);
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for(int i = 0; i < V; i++){
                adj.add(new ArrayList());
            }
            for(int i = 0; i < E; i++){
                String[] s = br.readLine().trim().split(" ");
                int u = Integer.parseInt(s[0]);
                int v = Integer.parseInt(s[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            BipartiteCheck obj = new BipartiteCheck();
            boolean ans = obj.isBipartite(V, adj);
            if(ans)
                System.out.println("1");
            else System.out.println("0");
        }
    }
}// } Driver Code Ends


class BipartiteCheck
{
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>>adj)
    {
        // Code here
        //Array to store 2 colors. This is also used as visited array
        int[] colors = new int[V];
        Arrays.fill(colors,-1);
        Queue<Integer> queue = new LinkedList();
        //Perform BFS on all nodes
        for(int i=0;i<V;i++) {
            //If already visited, need not perform BFS
            if(colors[i]!=-1){
                continue;
            }
            queue.add(i);
            colors[i] = 1;
            while (!queue.isEmpty()) {
                Integer e = queue.poll();
                for (int child : adj.get(e)) {
                    //if child not visited, assign color
                    if (colors[child] == -1) {
                        colors[child] = 1 - colors[e];
                        queue.add(child);
                    } else {
                        //If same color found on children, then it's not bipartite
                        if (colors[child] == colors[e]||child==e) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}