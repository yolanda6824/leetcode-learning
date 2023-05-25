// 递归专题
// 1.思考到了子节点应该返回什么
public class Recursion {
    // 1.求二叉树的深度
    public int depthOfTree(Node node) {
        if (node == null) {
            return 0;
        }
        return depth(node);
    }

    private int depth(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(depth(node.left), depth(node.right)) + 1;
    }
}
