//https://leetcode.com/problems/binary-tree-inorder-traversal/
package src.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }

class BinaryTreeInorderTraversal {
     //Inorder traversal of BST using Stack
    public List<Integer> inorderTraversal(TreeNode root) {
        //Create a stack
        Stack<TreeNode> stack = new Stack();
        //Initialize current with root
        TreeNode current = root;
        //List to store answer
        List<Integer> ans = new ArrayList();
        //Continue while current is not null or stack is not empty
        while(current!=null || !stack.isEmpty()){
            //Keep adding left nodes to the stack
            while(current!=null){
                stack.push(current);
                current = current.left;
            }
            //Get the top and add to list
            current = stack.pop();
            ans.add(current.val);
            //Move to right and repeat
            current = current.right;
        }
        return ans;
    }
}