import com.sun.javafx.collections.ListListenerHelper;
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

    @Test
    public void testPalindrome() {
        List<List<String>> result = recursion.getSubPalindrome("google");
        System.out.println(Arrays.toString(result.toArray()));
    }

    @Test
    public void testIpAddress() {
        List<String> result = recursion.splitIpAddress("25525511135");
        System.out.println(Arrays.toString(result.toArray()));
    }

    @Test
    public void countMinCost() {
        int result = recursion.countMinCostOfSteps(new int[]{1,100,1,1,100,1});
        int result1 = recursion.countMinCostOfSteps(new int[]{10,15,20});
        System.out.println("min cost:"+result1);
    }

    @Test
    public void countMaxSteal() {
        int max = recursion.countSubMaxRoundStealNum(new int[]{1,2,3,1});
        int max1 = recursion.countSubMaxRoundStealNum(new int[]{2,9,1,3,7});
        int max2 = recursion.countSubMaxRoundStealNum(new int[]{2,7,9,3,1});
        int max3 = recursion.countSubMaxRoundStealNum(new int[]{3,1,3,100});
        assert max == 4;
        assert max1 == 16;
        assert max2 == 12;
        assert max3 == 103;
        System.out.println("countMaxSteal:"+max3);
    }

    @Test
    public void countRoundMaxSteal() {
        int max = recursion.countRoundMaxStealNum(new int[]{2,9,1,4});
        assert max == 13;
        int max1 = recursion.countRoundMaxStealNum(new int[]{1,2,3});
        System.out.println("max1:"+max1);
        assert max1 == 3;
        int max3 = recursion.countRoundMaxStealNum(new int[]{2,9,7,3,1,7});
        assert max3 == 19;
        int max4 = recursion.countRoundMaxStealNum(new int[]{1,3,1,3,100});
        System.out.println("max4:"+max4);
    }

    /**
     * [[5,6,5],[15,8,8],[13,19,7],[16,1,9],[15,2,18],[13,18,8],[4,1,3],[3,3,3],[16,14,14],[7,6,1],[7,17,17],
     * [8,20,10],[12,16,1],[8,11,8],[14,7,12],[8,18,13],[6,2,3],[16,1,11],[4,2,10],[17,16,17],[1,8,17],[1,12,17],[1,11,10]]
     */
    @Test
    public void countMinPaintCost() {
        int[][] arr = new int[][]{{5,6,5},{15,8,8},{13,19,7},{16,1,9},{15,2,18},{13,18,8},{4,1,3},{3,3,3}};
        int minCost = recursion.countMinPaintCost(arr);
        //int minCost1 = recursion.countMinPaintCost(new int[][]{{17,2,8}});
        int minCostDp = recursion.countMinPaintCostDp(arr);
        //int minCost1Dp = recursion.countMinPaintCost(new int[][]{{17,2,17},{16,5,10},{14,3,19}});
        System.out.println(minCost +","+minCost+","+minCostDp+","+minCostDp);
        assert minCostDp == 48;
        assert minCost ==48;

    }

    @Test
    public void countMinTurnTime() {
        int time = recursion.countMinTurnTime("100000001010000");
        int time1 = recursion.countMinTurnTime("010110");
        System.out.println("time:"+time);
        System.out.println("time1:"+time1);
        assert time == 3;
    }
}
