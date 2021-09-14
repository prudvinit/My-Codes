package src.leetcode;

import java.util.Arrays;

public class BinaryTreeColoringGame {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    void dfs(TreeNode node,TreeNode tree[],int height[]){
        if(node==null){
            return;
        }
        tree[node.val] = node;

        if(node.left!=null){
            dfs(node.left,tree,height);
        }
        if(node.right!=null){
            dfs(node.right,tree,height);
        }

        height[node.val] = 1+(node.left!=null?height[node.left.val]:0)+(node.right!=null?height[node.right.val]:0);
    }

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        int height[] = new int[n+1];
        TreeNode tree[] = new TreeNode[n+1];
        dfs(root,tree,height);
        //Find left, right and top sizes
        int l = tree[x].left!=null?height[tree[x].left.val]:0;
        int r = tree[x].right!=null?height[tree[x].right.val]:0;
        int top = n - l - r - 1;
        //Check if sum of 2 branches is greater than the 3rd
        return top>(l+r+1) || l>(top+r+1) || r>(1+top+l);
    }

    static void test1(){
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(1,left,right);
        System.out.println(new BinaryTreeColoringGame().btreeGameWinningMove(root,3,1));
    }
    static void test2(){
        TreeNode nodes[] = new TreeNode[12];
        for(int i=1;i<=11;i++){
            nodes[i] = new TreeNode(i);
        }
        for(int i=1;i<=11;i++){

            nodes[i].left = 2*i+1<=11?nodes[2*i+1]:null;
            nodes[i].right = 2*i+2<=11?nodes[2*i+2]:null;
        }
        System.out.println(new BinaryTreeColoringGame().btreeGameWinningMove(nodes[1],11,3));
    }
    static void test3(){
        TreeNode nodes[] = new TreeNode[6];
        for(int i=1;i<nodes.length;i++){
            nodes[i] = new TreeNode(i);
        }
        nodes[1].left = nodes[2];
        nodes[1].right = nodes[3];
        nodes[2].left = nodes[4];
        nodes[2].right = nodes[5];
        System.out.println(new BinaryTreeColoringGame().btreeGameWinningMove(nodes[1],5,1));
    }
    static void test4(){
        TreeNode nodes[] = new TreeNode[6];
        for(int i=1;i<nodes.length;i++){
            nodes[i] = new TreeNode(i);
        }
        nodes[1].left = nodes[2];
        nodes[1].right = nodes[3];
        nodes[2].left = nodes[4];
        nodes[2].right = nodes[5];
        System.out.println(new BinaryTreeColoringGame().btreeGameWinningMove(nodes[1],5,2)+" Expected : false");

    }
    static void test5(){
        TreeNode nodes[] = new TreeNode[6];
        for(int i=1;i<nodes.length;i++){
            nodes[i] = new TreeNode(i);
        }
        nodes[1].left = nodes[2];
        nodes[1].right = nodes[3];
        nodes[2].left = nodes[4];
        nodes[2].right = nodes[5];
        System.out.println(new BinaryTreeColoringGame().btreeGameWinningMove(nodes[1],5,2)+" Expected : false");

    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
}
