//https://leetcode.com/problems/smallest-missing-genetic-value-in-each-subtree/
package src.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmallestMissingGeneticValueInEachSubtree {

    static class Node{
        int ind,val,ans;
        List<Node> child;
        Node(int ind, int val){
            this.ind = ind;
            this.val = val;
            this.ans = 1;
            this.child = new ArrayList();
        }
    }

    void dfs(Node node, boolean visited[], int mark[]){
        if(node==null){
            return;
        }
        visited[node.ind] = true;
        mark[node.val] = 1;
        for(Node child : node.child) {
            if(!visited[child.ind]){
                dfs(child,visited,mark);
            }
        }
    }

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = nums.length;
        Node tree[] = new Node[n];
        int ans[] = new int[n];
        int one=-1;
        for(int i=0;i<n;i++){
            tree[i] = new Node(i,nums[i]);
            ans[i] = 1;
            if(nums[i]==1){
                one = i;
            }
        }
        if(one==-1){
            return ans;
        }
        for(int i=1;i<n;i++){
            tree[parents[i]].child.add(tree[i]);
        }
        boolean visited[] = new boolean[n];
        int[] mark = new int[10];
        int prev = 2;
        while (one!=-1){
            dfs(tree[one],visited,mark);
//            System.out.println("After node "+tree[one].ind+" "+Arrays.toString(mark));
            for(int i=prev;i<mark.length;i++){
                if(mark[i]!=1){
                    tree[one].ans = i;
//                    prev = i+1;
                    break;
                }
            }
            one = parents[one];
        }



        for(int i=0;i<n;i++){
            ans[i] = tree[i].ans;
        }
        return ans;
    }

    static void test1(){
        int[] parents = new int[]{-1,0,1,0,3,3};
        int[] nums = new int[]{5,4,6,2,1,3};
        System.out.println(Arrays.toString(new SmallestMissingGeneticValueInEachSubtree().smallestMissingValueSubtree(parents,nums)));
    }

    static void test2(){
        int[] parents = new int[]{-1,0,0,2};
        int[] nums = new int[]{1,2,3,4};
        System.out.println(Arrays.toString(new SmallestMissingGeneticValueInEachSubtree().smallestMissingValueSubtree(parents,nums)));
    }

    static void test3(){
        int[] parents = new int[]{-1,2,3,0,2,4,1};
        int[] nums = new int[]{2,3,4,5,6,7,8};
        System.out.println(Arrays.toString(new SmallestMissingGeneticValueInEachSubtree().smallestMissingValueSubtree(parents,nums)));
    }

//    [-1,0,0,0,2]
//            [6,4,3,2,1]

    static void test4(){
        int[] parents = new int[]{-1,0,0,0,2};
        int[] nums = new int[]{6,4,3,2,1};
        System.out.println(Arrays.toString(new SmallestMissingGeneticValueInEachSubtree().smallestMissingValueSubtree(parents,nums)));
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }
}
