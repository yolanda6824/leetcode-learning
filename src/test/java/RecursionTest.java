import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class RecursionTest {
    Recursion recursion = new Recursion();

    @Test
    public void testDepth() {
        Node root = Node.buildTree(new int[]{1,2,3,4,0,5,0,0,6});
        Node.level(root);
        int depth = recursion.depthOfTree(root);
        System.out.println("final depth:"+depth);
    }

    @Test
    public void testSubTree() {
        Node rootA = Node.buildTree(new int[]{3,4,5,1,2,0,0,0,0,0,3});
        Node rootB = Node.buildTree(new int[]{4,3});
        boolean result = recursion.isSubTree(rootA, rootB);
        System.out.println("testSubTree:"+result);
    }

    @Test
    public void testSymmetric() {
        Node node = Node.buildTree(new int[]{1,2,2,3,4,4,4});
        boolean result = recursion.isSymmetricTree(node);
        System.out.println("testSymmetric:"+result);
    }

    @Test
    public void isBalancedTree() {
        Node node = Node.buildTree(new int[]{1,2,2,3,3,0,0,4,4});
        Node node1 = Node.buildTree(new int[]{1,2,2,3,31});
        boolean result = recursion.isBalancedTree(node);
        boolean result1 = recursion.isBalancedTree(node1);
        System.out.println("isBalancedTree:"+result+","+result1);
    }

    @Test
    public void testSumK() {
        int[] numbers = new int[]{2,3,5};
        List<List<Integer>> result = recursion.getSubsetEqualK(numbers, 8);
        System.out.print("testSumK:"+ Arrays.toString(result.toArray()));
    }
}
