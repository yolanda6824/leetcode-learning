
import java.util.ArrayList;
import java.util.LinkedList;

public class Node {
    public int value = 0;
    public Node left = null;
    public Node right = null;

    public Node(int value) {
        this.value = value;
    }

    public static void printTree(Node node) {
        if (node == null) {
            System.out.println("the tree is null");
            return;
        }
        dfs(node);
    }

    // 先序遍历
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value+",");
        dfs(node.left);
        dfs(node.right);
    }

    public static void level(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            Node node = nodeQueue.pop();
            if (node.left != null) {
                nodeQueue.add(node.left);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
            }
            System.out.print(node.value+",");
        }
        System.out.println();
    }

    // [1,1,1,1,0,1,0]
    public static Node buildTree(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Node root = new Node(arr[0]);
        ArrayList<Node> nodeQueue = new ArrayList<>();
        nodeQueue.add(root);
        int curIndex = 1;
        while (curIndex < arr.length) {
            Node leftNode = arr[curIndex] == 0 ? null : new Node(arr[curIndex]);
            curIndex++;
            Node rightNode = (curIndex < arr.length && arr[curIndex] != 0) ? new Node(arr[curIndex]) : null;
            curIndex++;
            Node curNode = nodeQueue.remove(0);
            if (curNode != null) {
                curNode.left = leftNode;
                curNode.right = rightNode;
            }
            nodeQueue.add(leftNode);
            nodeQueue.add(rightNode);
        }
        return root;
    }
}
