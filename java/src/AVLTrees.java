package src;

class Node {
    int val;
    Node left, right;
    int height;

    Node(int val) {
        this.val = val;
        this.height = 0;
    }

    @Override
    public String toString() {
        return "(" + val + " [" + (left != null ? left.val : null) + "," + (right != null ? right.val : null) + "," + height + "] )";
    }
}

public class AVLTrees {
    Node root;

    //Method to extract height. To avoid having to null check multiple times
    private int height(Node root){
        return root==null?0:root.height;
    }

    
    private Node rotateRight(Node root) {
        //When we rotate right, new root will be the left
        Node newRoot = root.left;
        //Root's left will be new root's right
        root.left = newRoot.right;
        //new root's right will be root
        newRoot.right = root;
        //Update the heights of root and new root
        root.height = Math.max(height(root.left),height(root.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left),height(newRoot.right)) + 1;
        return newRoot;
    }

    //This method is the mirror of the above method
    private Node rotateLeft(Node root) {
        Node newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        root.height = Math.max(height(root.left),height(root.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left),height(newRoot.right)) + 1;
        return newRoot;
    }

    private Node insert(Node root, Node data) {
        //For single node, return the same node
        if (root == null) {
            data.height=1;
            return data;
        }
        //Recursively insert into either left or right
        if (data.val < root.val) {
            root.left = insert(root.left, data);
        }
        else {
            root.right = insert(root.right, data);
        }
        //Update height
        root.height = 1 + Math.max((root.left != null ? root.left.height : 0), (root.right != null ? root.right.height : 0));
        //Get the heights of left and right sub trees
        int leftSize = height(root.left);
        int rightSize = height(root.right);
        if (Math.abs(leftSize - rightSize) <= 1) {
            return root;
        }
        //Check if L
        if (leftSize > rightSize) {
            Node temp = root.left;
            int tempLeft = height(temp.left);
            int tempRight = height(temp.right);
            //LL Case
            if(tempLeft>=tempRight){
                return rotateRight(root);
            }
            //LR Case
            else{
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
        }
        else{
            Node temp = root.right;
            int tempLeft = height(temp.left);
            int tempRight = height(temp.right);
            //RR Case
            if(tempRight>=tempLeft){
                return rotateLeft(root);
            }
            //RL Case
            else{
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }
        }
    }

    public void insert(int val) {
        Node n = new Node(val);
        root = insert(root, n);
    }

    public void print() {
        print(root);
        System.out.println();
    }

    //Method for inorder traversal
    private void print(Node root) {
        if (root == null) {
            return;
        }
        print(root.left);
        System.out.print(root + " ");
        print(root.right);
    }
}

class AVLTester {

    static void test1(){
        AVLTrees avl = new AVLTrees();
        int arr[] = {2, 1, 3, -1, 0};
        for (int i = 0; i < arr.length; i++) {
            avl.insert(arr[i]);
        }
        avl.print();
    }

    static void test2(){
        AVLTrees avl = new AVLTrees();
        int arr[] = {1,2,3,4,5};
        for (int i = 0; i < arr.length; i++) {
            avl.insert(arr[i]);
        }
        avl.print();
    }
    public static void main(String[] args) {
        test1();
        test2();
    }
}