//https://www.geeksforgeeks.org/m-coloring-problem-backtracking-5/
package src;

import java.util.*;

public class MColoringBacktracking {

    public static boolean graphColoring(List<Integer>[] G, int[] color, int i, int C)
    {
        //If all vertices are reached, this means the configuration is valid
        if(i==G.length){
            return true;
        }
        //Assign each color from 0 to C
        for(int d=0;d<C;d++){
            color[i] = d;
            boolean valid = true;
            //Check if color d is a valid by comparing colors for this child
            for(int child : G[i]){
                if(color[child]==d){
                    valid = false;
                    break;
                }
            }
            //If valid, recurse with the next node
            if(valid && graphColoring(G, color, i + 1, C)) {
                return true;
            }
            //Once complete if the config not valid, revert the color
            color[i] = -1;
        }
        return false;
    }
}

class MColoringProblemTester {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int tc = scan.nextInt();

        while (tc-- > 0) {
            int V = scan.nextInt();
            int C = scan.nextInt();
            int E = scan.nextInt();

            List<Integer>[] G = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                G[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                int u = scan.nextInt() - 1;
                int v = scan.nextInt() - 1;
                G[u].add(v);
                G[v].add(u);
            }
            int[] color = new int[V];

            System.out.println(new MColoringBacktracking().graphColoring(G, color, 0, C) ? 1 : 0);
        }
    }
}