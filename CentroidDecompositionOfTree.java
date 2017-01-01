package centroiddecompositionoftree;

import java.io.*;
import java.util.*;

/**
 *
 * @author prudvi.maddala@oracle.com
 */
public class CentroidDecompositionOfTree{

    /**
     * @param args the command line arguments
     */
    
    //This array list stores our initial tree
    static ArrayList<Integer> tree[];
    
    //This array stores sizes of the subtree
    static int size[];
    
    /*
    * This method calculates the sizes of subtree. This is easy
    */
    static void runDFSAndCalculateSubtreeSizes(int u,int p){
        size[u] = 1;
        for(int x : tree[u]){
            if(x!=p&&!decomposed[x]){
                runDFSAndCalculateSubtreeSizes(x, u);
                size[u]+=size[x];
            }
        }
    }
    
    /*
    * This method returns the centroid of the tree
    */
    static int findCentroidOfTheTree(int u, int p, final int SIZE){
        for(int x : tree[u]){
            //If size of the current subtree is greater than half the size, centroid lies in this subtree
            if(x!=p&&size[x]>SIZE/2&&!decomposed[x]){
                return findCentroidOfTheTree(x, u, SIZE);
            }
        }
        return u;
    }
    
    //This array stores the parent nodes of the node in our constructed centroid tree
    static int parent[];
    
    //This array sets whether a node has become centroid yet or not. 
    //This is for speeding up the algo rather than removig the edges which will take considerably more time
    static boolean decomposed[];
    
    /*
    * This node performs the centroid decomposition of the tree
    */
    static void performCentroidDecompositionOfTheTree(int u, int p, final int CENTROID){
        runDFSAndCalculateSubtreeSizes(u,u);
        int centroid = findCentroidOfTheTree(u,p,size[u]);
        decomposed[centroid] = true;
        parent[centroid] = CENTROID;
        for(int i=0;i<tree[centroid].size();i++){
            int c = tree[centroid].get(i);
            if(c!=p&&!decomposed[c]) {
                performCentroidDecompositionOfTheTree(c, -1, centroid);
            }
        }
    }
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        InputReader ir = new InputReader();
        int n = ir.readInt();
        tree = new ArrayList[n+1];
        size = new int[n+1];
        parent = new int[n+1];
        decomposed = new boolean[n+1];
        for(int i=0;i<=n;i++){
            tree[i] = new ArrayList();
        }
        for(int i=0;i<n-1;i++){
            int u = ir.readInt();
            int v = ir.readInt();
            tree[u].add(v);
            tree[v].add(u);            
        }
        
        performCentroidDecompositionOfTheTree(1,-1,0);
        System.out.println("Centroid tree is ");
        for(int i=1;i<=n;i++){
            System.out.println(i+" "+parent[i]);
        }
    }    
}
