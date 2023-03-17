package src.google2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class TreeNode {
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

public class DuplicateSubtrees {

    private String postOrder(TreeNode node, Map<String,Integer> map, List<TreeNode> ans){
        if(node==null)return "#";
        String gen = postOrder(node.left,map,ans)+postOrder(node.right,map,ans)+node.val;
        map.put(gen,map.getOrDefault(gen,0)+1);
        if(map.get(gen)==2){
            ans.add(node);
        }
        return gen;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String,Integer> map = new HashMap();
        List<TreeNode> ans = new ArrayList();
        postOrder(root,map,ans);
        return ans;
    }
}
