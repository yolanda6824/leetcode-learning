import org.junit.jupiter.api.Test;

public class RecursionTest {
    @Test
    public void testDepth() {
        Recursion recursion = new Recursion();
        Node root = Node.buildTree(new int[]{1,2,3,4,0,5,0,0,6});
        Node.level(root);
        int depth = recursion.depthOfTree(root);
        System.out.println("final depth:"+depth);
    }
}
