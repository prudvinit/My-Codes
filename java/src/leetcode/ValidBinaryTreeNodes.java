package src.leetcode;

import java.util.*;

public class ValidBinaryTreeNodes {
    int parent[];
    int find(int a){
        if(parent[a]!=a){
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }
    void union(int a, int b){
        parent[find(a)] = parent[find(b)];
    }
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int edges =  0;
        int indegree[] = new int[n];
//        int graph[][] = new int[n][n];
        Set<String> e = new HashSet();
        int ind = 0;
        System.out.println("Left "+Arrays.toString(leftChild));
        for(int child : leftChild){
//            System.out.println("Lty");
            System.out.println("Lefty : "+ind+" "+child);
            if(child!=-1){
                if(e.contains(child+" "+ind)){
                    System.out.println("Exists");
                    return false;
                }
                e.add(child+" "+ind);
//                graph[ind][child]++;
                edges++;
                indegree[child]++;
                System.out.println("Ind Child "+child+" "+indegree[child]);
                if(indegree[child]>1){
                    System.out.println("IND LEFT");
                    return false;
                }
            }
            // indegree
            ind++;
        }
        ind=0;
        for(int child : rightChild){
            if(child!=-1){
                if(e.contains(child+" "+ind)){
                    System.out.println("Exists");
                    return false;
                }
                e.add(child+" "+ind);
                edges++;
                indegree[child]++;
                System.out.println("Child is "+child+" "+indegree[child]);
                if(indegree[child]>1){
                    System.out.println("IND");
                    return false;
                }
            }
            ind++;
        }
        parent = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
//        Arrays.fill(parent,-1);
        for(int i=0;i<n;i++){
            if(leftChild[i]!=-1) {
                System.out.println("Union "+i+" "+leftChild[i]);
                union(i, leftChild[i]);
            }
            if(rightChild[i]!=-1) {
                union(i, rightChild[i]);
            }
        }
//        System.out.println("Parents "+p);
        for(int i=0;i<n;i++){
            if(find(i)!=find(0)){
                System.out.println("Don");
                return false;
            }
        }
        System.out.println(edges);

        return edges==n-1;

    }

    public static void main(String[] args) {
        System.out.println(new ValidBinaryTreeNodes().validateBinaryTreeNodes(4, new int[]{1,2,0,1}, new int[]{-1,-1,-1,-1}));
        System.out.println(new ValidBinaryTreeNodes().validateBinaryTreeNodes(4, new int[]{1,-1,3,-1}, new int[]{2,-1,-1,-1}));
    }
}
