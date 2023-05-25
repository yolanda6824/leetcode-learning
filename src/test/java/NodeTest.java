import org.junit.jupiter.api.Test;

public class NodeTest {
    @Test
    public void testNodeBuildTree() {
        int[] arr = new int[]{1,2,3,0,4,5,0};
        Node root = Node.buildTree(arr);
        Node.printTree(root);
        System.out.println("========");
        Node.level(root);
    }
}
