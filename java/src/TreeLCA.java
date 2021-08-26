package src;

class TreeNode {
    int data;
    TreeNode left, right;

    TreeNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data + "";
    }
}

public class TreeLCA {
    TreeNode LCA(TreeNode root, int a, int b) {
        if (root == null) {
            return null;
        }
        if (a == root.data || b == root.data) {
            return root;
        }
        TreeNode left = LCA(root.left, a, b);
        TreeNode right = LCA(root.right, a, b);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(4);
        TreeLCA l = new TreeLCA();
        System.out.println("Expected : 1 , Actual : "+l.LCA(root, 2, 5));
        System.out.println("Expected : 6 , Actual : "+l.LCA(root, 2, 4));
        System.out.println("Expected : 6 , Actual : "+l.LCA(root, 2, 3));
        System.out.println("Expected : 3 , Actual : "+l.LCA(root, 4, 3));
        System.out.println("Expected : 6 , Actual : "+l.LCA(root, 5,6));
    }
}
