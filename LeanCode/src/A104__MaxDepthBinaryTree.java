public class A104__MaxDepthBinaryTree {

    //        [1, 2, 3, 4, 6, 8, 9, 10, 15, 16, 20, 25]
    public static int maxDepth(TreeNode root) {
        return (root == null ? 0 : calc3(root));
    }

    // WORKS !!!!!!
    public static int calc(TreeNode root, int currentLevel) {
        int leftDepth = 0;
        int rightDepth = 0;
        if (root.left != null) {
            leftDepth = calc(root.left, 1);
        }
        if (root.right != null) {
            rightDepth = calc(root.right, 1);
        }
        System.out.println("calc -- > CURLevel=" + currentLevel + "_node=" + root.val +
                " -->> rightDepth=" + rightDepth + ", leftDepth=" + leftDepth);
        return (leftDepth < rightDepth ? currentLevel + rightDepth : currentLevel + leftDepth);
    }

    public static int calc2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = 0;
        int rightDepth = 0;
        leftDepth += calc2(root.left);
        rightDepth += calc2(root.right);
        return 1+Math.max(leftDepth, rightDepth);
    }

    public static int calc3(TreeNode root) {
        return (root == null ? 0 : 1+Math.max(calc3(root.left), calc3(root.right)));
    }

    public static TreeNode insert(TreeNode root, int... values) {
        TreeNode r = insert(root, values[0]);
        for (int i = 1; i < values.length; i++) {
            insert(r, values[i]);
        }
        return r;
    }

    public static TreeNode insert(TreeNode root, int nodeValue) {
        TreeNode newNode = new TreeNode(nodeValue);
        if (root == null) {
            root = newNode;
            return root;
        }
        TreeNode current = root;
        TreeNode parent = null;
        while (true) {
            parent = current;
            if (nodeValue < current.val) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return root;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return root;
                }
            }
        }
    }

    public static void display(TreeNode root) {
        if (root != null) {
            display(root.left);
            System.out.print(root);
            display(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = insert(null, 3, 8, 1, 4, 6, 2, 10, 9, 20, 25, 15, 16);
        display(root);
        System.out.println();
        System.out.println("\n1 DEPTH=" +  (root == null ? 0 : calc(root,1)));
        System.out.println("\n2 DEPTH=" + maxDepth(root));
    }
}

//     Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "(" + val + ") ";
    }
}
