//https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
/**
 * The key observation in this problem is , there are no loops. There can be dimonds (Dimond shaped directed graphs).
 * If we found answer for a node, we can use the answer for other cases.
 */
package src.leetcode;

import java.util.ArrayList;

public class LongestIncreasingPathInMatrix {
    static class Node{
        int val,ind;
        ArrayList<Node> child = new ArrayList();
        Node(int val,int ind){
            this.val = val;
            this.ind = ind;
        }
    }

    int dfs(Node[] graph, int node, int dp[]){
        //Check if answer for this node is already found. This also mean that this node is visited.
        if(dp[node]!=0){
            return dp[node];
        }
        int ans = 1;
        //Loop through all child
        for(Node child : graph[node].child){
            //Find the maximum path from all children
            ans = Math.max(ans,1+dfs(graph,child.ind,dp));
        }
        //Update the maximum path from this node in dp
        dp[node] = ans;
        return dp[node];
    }
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        ArrayList<Node> graph[] = new ArrayList[n*m];
        Node nodes[] = new Node[n*m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                nodes[i*m+j] = new Node(matrix[i][j],m*i+j);
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(j>=1) {
                    int left = matrix[i][j - 1];
                    if(left>matrix[i][j]){
                        nodes[i*m+j].child.add(nodes[i*m+j-1]);
                    }
                }
                if(j+1<m) {
                    int right = matrix[i][j + 1];
                    if(right>matrix[i][j]){
                        nodes[i*m+j].child.add(nodes[i*m+j+1]);
                    }
                }
                if(i>=1) {
                    int top = matrix[i - 1][j];
                    if(top>matrix[i][j]){
                        nodes[i*m+j].child.add(nodes[(i-1)*m+j]);
                    }
                }
                if(i+1<n) {
                    int bottom = matrix[i + 1][j];
                    if(bottom>matrix[i][j]){
                        nodes[i*m+j].child.add(nodes[(i+1)*m+j]);

                    }
                }
            }
        }
        int ans = 1;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int dp[] = new int[nodes.length];
                if(i==1&&j==3) {
                    int path = dfs(nodes, m * i + j, dp);
                    ans = Math.max(ans, path);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{{9,9,4},{6,6,8},{2,1,1}};
//        System.out.println(new LongestIncreasingPathInMatrix().longestIncreasingPath(mat));
//        mat = new int[][]{{3,4,5},{3,2,6},{2,2,1}};
//        System.out.println(new LongestIncreasingPathInMatrix().longestIncreasingPath(mat));
//        mat = new int[][]{{1}};
//        System.out.println(new LongestIncreasingPathInMatrix().longestIncreasingPath(mat));
        mat = new int[][]{{7,6,1,1},{2,7,6,0},{1,3,5,1},{6,6,3,2}};
        System.out.println(new LongestIncreasingPathInMatrix().longestIncreasingPath(mat));

    }
}
