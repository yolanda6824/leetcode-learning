import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    // 2.判断B树是否为A树的子结构
    // 1.遍历的条件：当A树的根节点 == B树的根节点，递归看A的子节点 == B的子节点，当A的根节点!=B的根节点，看A的子节点 == B的根节点
    // 2.子树的条件：看A,B的根节点一致，且先序遍历结果一致。
    public boolean isSubTree(Node rootA, Node rootB) {
        if (rootB == null) {
            return false;
        }
        return querySubTree(rootA, rootB);
    }

    private boolean querySubTree(Node rootA, Node rootB) {
        if (rootA == null && rootB == null) {// 1.先考虑根节点，应该返回什么值
            return true;
        }
        if (rootB == null) {
            return true;
        }
        if (rootA == null) {
            return false;
        }
        if (rootA.value != rootB.value || !judgeSubTree(rootA, rootB)) {
            return querySubTree(rootA.left, rootB) || querySubTree(rootA.right, rootB);
        }
        // 如果不等就继续往下搜索
        return true;
    }

    private boolean judgeSubTree(Node rootA, Node rootB) {
        if (rootA == null && rootB == null) {// 1.先考虑根节点，应该返回什么值
            return true;
        }
        if (rootB == null) {
            return true;
        }
        if (rootA == null) {
            return false;
        }
        if (rootA.value == rootB.value) {// 再考虑自上往下
            return judgeSubTree(rootA.left, rootB.left) && judgeSubTree(rootA.right, rootB.right);
        } else {
            return false;
        }
    }

    // 3.判断A是否为对称二叉树，即A == A的镜像
    public boolean isSymmetricTree(Node rootA) {
        if (rootA == null) {
            return true;
        }
        return isTreeEqual(rootA.left, rootA.right);
    }

    private boolean isTreeEqual(Node rootA, Node rootB) {
        if (rootA == null && rootB == null) {
            return true;
        }
        if (rootA == null || rootB == null) {
            return false;
        }
        if (rootA.value == rootB.value) {
            return isTreeEqual(rootA.left, rootB.right) && isTreeEqual(rootA.right, rootB.left);
        } else {
            return false;
        }
    }

    // 4.判断该二叉树是否为平衡二叉树，左右子树的深度差值不超过1
    // 且每个节点都是平衡二叉树
    public boolean isBalancedTree(Node root) {
        if (root == null) {
            return true;
        }
        return Math.abs(getTreeDepth(root.left) - getTreeDepth(root.right)) <= 1
                && isBalancedTree(root.left) && isBalancedTree(root.right);
    }

    private int getTreeDepth(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getTreeDepth(root.left), getTreeDepth(root.right)) + 1;
    }

    /**
     * 求一个集合的所有子集
     * @param numbers
     * @return
     */
    public List<List<Integer>> getSubCollection(int[] numbers) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> subset = new LinkedList<>();
        getCollection(result, subset, 0, numbers);
        return result;
    }

    private void getCollection(List<List<Integer>> result, List<Integer> subset, int index, int[] numbers) {
        if (index == numbers.length - 1) {
            result.add(subset);
        } else {
            getCollection(result, subset, index + 1, numbers);
            subset.add(numbers[index + 1]);
            getCollection(result, subset, index + 1, numbers);
            subset.remove(subset.size() - 1);
        }
    }

    // 回溯：
    // 1.结束条件
    // 2.每次一个递归产生一个分支
    // 3.如果父节点要使用子节点的结果，要用return
    // 4.如果只是要收集子节点的结果，使用变量统一
    public List<String> getBrackets(int n) {
        LinkedList<String> bracketsResult = new LinkedList<>();
        collectBrackets(bracketsResult, "", 0, n, 0, 0);
        return bracketsResult;
    }

    private void collectBrackets(List<String> bracketsResult, String curStr, int index, int maxNum, int leftUsed, int rightUsed) {
        if (index == maxNum * 2) {
            bracketsResult.add(curStr);
            return;
        }
        if (index == 0 || rightUsed == leftUsed) {
            collectBrackets(bracketsResult, curStr + "(", index + 1, maxNum, leftUsed + 1, rightUsed);
        } else if (leftUsed == maxNum) {
            collectBrackets(bracketsResult, curStr + ")", index + 1, maxNum, leftUsed, rightUsed + 1);
        } else {
            collectBrackets(bracketsResult, curStr + "(", index + 1, maxNum, leftUsed + 1, rightUsed);
            collectBrackets(bracketsResult, curStr + ")", index + 1, maxNum, leftUsed, rightUsed + 1);
        }
    }

    /**
     * 获取一个集合中，只含有k个数字的子集
     * @return
     */
    public List<List<Integer>> getKSubCollection(int[] numbers, int k) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> subset = new LinkedList<>();
        getSubKCollection(result, subset, numbers, 0, k);
        return result;
    }

    /**
     * 递归用到集合的要记得回退，否则尽量都用临时变量
     * @param result
     * @param subset
     * @param numbers
     * @param index
     * @param k
     */
    private void getSubKCollection(List<List<Integer>> result, List<Integer> subset, int[] numbers, int index, int k) {
        if (subset.size() == k) {
            result.add(subset);
            return;
        }
        // 子集可以选择添加numbers[index]，也可以选择添加空集
        getSubKCollection(result, subset, numbers, index + 1, k);

        subset.add(numbers[index + 1]);
        getSubKCollection(result, subset, numbers, index, k);
        // 搞完两个叶子节点，需要将subset回退到父节点原来的状态
        subset.remove(subset.size() - 1);
    }

    /**
     * 获取集合中总和==k的子集，数字可重复
     * @param numbers
     * @param k
     * @return 思路，即每选择一个数字，都可以展开numbers.length个分支，即调用numbers.length次递归
     * 但这样，会存在重复的组合，因此在最后，需要筛选出重复的组合
     */
    public List<List<Integer>> getSubsetEqualK(int[] numbers, int k) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> subset = new LinkedList<>();
        getSubset(result, subset, numbers, k);
        return result;
    }

    private void getSubset(List<List<Integer>> result, List<Integer> subset, int[] numbers, int leastSum) {
        if (leastSum == 0) {
            result.add(new LinkedList<>(subset));
            return;
        }
        if (leastSum < 0) {
            return;
        }
        for (int num : numbers) {
            if (num <= leastSum) {
                subset.add(num);
                getSubset(result, subset, numbers, leastSum - num);
                subset.remove(subset.size() - 1);
            }
        }
    }

    /**
     * 求子集之和 == k的集合
     * 因为集合的特点就在于，这个元素可以在/不在，所以如果规定顺序，就会变成排列
     * @param numbers
     * @param k
     * @return
     */
    /*public List<List<Integer>> getSubsetEqualK1(int[] numbers, int k) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> subset = new LinkedList<>();
        getSubset1(result, subset, numbers, k);
        return result;
    }

    private void getSubset1(List<List<Integer>> result, List<Integer> subset, int[] numbers, int leastSum) {
        if (leastSum == 0) {
            result.add(new LinkedList<>(subset));
            return;
        }
        if (leastSum < 0) {
            return;
        }

    }*/

    /**
     * 给定包含重复数字的集合，找出元素之和==k的子集，子集不可以重复
     */
    /**
     * 求子集之和 == k的集合
     * 因为集合的特点就在于，这个元素可以在/不在，所以如果规定顺序，就会变成排列
     * @param numbers
     * @param k
     * @return
     */
    public List<List<Integer>> getSubsetEqualK1(int[] numbers, int k) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> subset = new LinkedList<>();
        getSubset1(result, subset, numbers, 0,  k);
        return result;
    }

    /**
     * 这个的思路是：
     * 只考虑当前步骤，添加这个元素的下一步，是什么，不添加这个元素的下一步是什么
     * @param result
     * @param subset
     * @param numbers
     * @param leastSum
     */
    private void getSubset1(List<List<Integer>> result, List<Integer> subset, int[] numbers, int index, int leastSum) {
        if (leastSum == 0) {
            result.add(new LinkedList<>(subset));
            return;
        }
        if (leastSum < 0 || index >= numbers.length) {
            return;
        }
        // subSet是用来装最终结果的，这里代表subset当前集选择不添加当前index
        getSubset1(result, subset, numbers, index + 1, leastSum);
        // subset选择添加index
        subset.add(numbers[index]);
        // 因为下一步，可以重新选择当前index，所以index还是一样
        getSubset1(result, subset, numbers, index, leastSum - numbers[index]);
        subset.remove(subset.size() - 1);
    }

    /**
     * 获取回文字符串
     * @return
     */
    public List<List<String>> getSubPalindrome(String str) {
        List<List<String>> result = new LinkedList<>();
        List<String> subCollection = new LinkedList<>();
        getPalindrome(result, subCollection, str, "", 0);
        return result;
    }

    private void getPalindrome(List<List<String>> result, List<String> subCollection, String str, String subStr, int index){
        if (index == str.length()) {
            if (!subCollection.isEmpty()) {
                result.add(subCollection);
            }
            return;
        }
        if (isPalindrome(subStr)) {
            subCollection.add(subStr);
            getPalindrome(result, subCollection, str, "", index);
            return;
        }
        for (int i = index; i < str.length(); i++) {
            getPalindrome(result, subCollection, str, subStr + str.charAt(i), i + 1);
        }
    }

    private boolean isPalindrome(String str) {
        if (str.length() == 0) {
            return false;
        }
        if (str.length() == 1) {
            return true;
        }
        int fromIndex = 0;
        int toIndex = str.length() - 1;
        while (fromIndex <= toIndex) {
            if (str.charAt(fromIndex) == str.charAt(toIndex)) {
                fromIndex++;
                toIndex--;
            } else {
                return false;
            }
        }
        return true;
    }

    public List<String> splitIpAddress(String ip) {
        List<String> result = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        splitAddress(result, ip, 4, builder);
        return result;
    }

    private void splitAddress(List<String> result, String ip, int leastTime, StringBuilder builder) {
        if (leastTime == 0) {// 结束条件
            if (ip.equals("")) {
                String finalStr = builder.toString();
                result.add(finalStr.substring(0, finalStr.length() - 1));
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (i <= ip.length() - 1) {
                String subStr = ip.substring(0, i + 1);
                int realNumber = Integer.parseInt(subStr);
                if (!(subStr.startsWith("0") && subStr.length() > 1) && realNumber >= 0 && realNumber <= 255) {
                    builder.append(subStr).append(".");
                    splitAddress(result, ip.substring(i + 1), leastTime - 1, builder);
                    builder.delete(builder.toString().length() - subStr.length() - 1, builder.toString().length());
                }
            }
        }
    }


    /**
     * 以下是递归转移
     */
    /**
     * 求爬楼梯的最小代价，一次可以走一格，或两格
     * @param numbers
     * @return
     */
    public int countMinCostOfSteps(int[] numbers) {
        int length = numbers.length;
        if (length <= 1) {
            return 0;
        }
        if (length == 2) {
            return Math.min(numbers[0],numbers[1]);
        }
        int cost0 = numbers[0], cost1 = numbers[1];
        int result = 0;
        for (int i = 2; i < length; i++) {
            result = Math.min(cost0, cost1) + numbers[i];
            cost0 = cost1;
            cost1 = result;
        }
        return Math.min(cost0, cost1);
    }

    // 状态方程写错了
    // 可以变成fn = max(fn-1,fn-2+Fn)
    public int countStealMaxNum(int[] numbers) {
        int length = numbers.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return numbers[0];
        }
        if (length == 2) {
            return Math.max(numbers[0], numbers[1]);
        }
        if (length == 3) {
            return Math.max(numbers[0] + numbers[2], numbers[1]);
        }
        int steal0 = numbers[0];
        int steal1 = numbers[1];
        int steal2 = steal0 + numbers[2];
        int result = 0;
        for (int i = 3; i < length; i++) {
            result = Math.max(steal1, steal0) + numbers[i];
            steal0 = steal1;
            steal1 = steal2;
            steal2 = result;
        }
        return Math.max(steal1, steal2);
    }

    // f(length) = f(n-2),选第一个就不选最后一个
    public int countRoundMaxStealNum(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0],nums[1]);
        }
        return Math.max(countSubMaxRoundStealNum(
                Arrays.copyOfRange(nums, 0, nums.length - 1)),
                countSubMaxRoundStealNum(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    /**
     * 计算不相邻房子的最多偷窃金额的第二种算法
     * @param nums
     * @return
     */
    public int countSubMaxRoundStealNum(int[] nums) {

        int steal0 = nums[0];
        int steal1 = Math.max(nums[1],nums[0]);// 注意这一步
        int result = 0;
        for (int i = 2; i < nums.length; i++) {
            result = Math.max(steal1, steal0 + nums[i]);
            steal0 = steal1;
            steal1 = result;
        }
        return Math.max(steal0, steal1);
    }

    /**
     * 噢，递归解法，超出时间限制，那我用动态规划试试
     * @param nums
     * @return
     */
    public int countMinPaintCost(int[][] nums) {
        if (nums.length <= 0) {
            return 0;
        }
        return countSubMinPaintCost(nums, -1);
    }

    /**
     * 有一点点乱
     */
    private int countSubMinPaintCost(int[][] nums, int usedIndex) {
        if (nums.length == 0) {
            return 0;
        }
        int[] curLevel = nums[0];
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < curLevel.length; i++) {
            if (i != usedIndex) {
                minCost = Math.min(curLevel[i] + countSubMinPaintCost(Arrays.copyOfRange(nums, 1, nums.length), i), minCost);
            }
        }
        return minCost;
    }

    public int countMinPaintCostDp(int[][] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length >= 2) {
            for (int i = 1; i < nums.length; i++) {
                for (int k = 0; k < nums[0].length; k++) {
                    nums[i][k] = nums[i][k] + countLevelMinValue(nums[i-1], k);
                }
            }
            return countLevelMinValue(nums[nums.length - 1], -1);
        }
        return countLevelMinValue(nums[0], -1);
    }

    private int countLevelMinValue(int[] nums, int expectedIndex) {
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (i != expectedIndex) {
                minCost = Math.min(nums[i], minCost);
            }
        }
        return minCost;
    }

    /**
     * 计算01字符串翻转的最小次数
     * 用数组模拟表格
     * @param str
     * @return
     */
    public int countMinTurnTime(String str) {
        if (str.length() <= 1) {
            return 0;
        }
        int[] arr = new int[]{str.charAt(0) == '0' ? 0 : 1,str.charAt(0) == '1' ? 0 : 1};
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                arr[1] = Math.min(arr[0], arr[1]) + 1;
            } else {
                arr[1] = Math.min(arr[0], arr[1]);
                arr[0] += 1;
            }
        }
        return Math.min(arr[0], arr[1]);
    }

    /**
     * 求最长的斐波那契数列长度
     * f(i,j) = f(j,k) + (a[j]+a[k] == a[i]? 1 : 0)
     * @param arr
     * @return
     */
    /*public int countLongestFibLength(int[] arr) {
        int[][] result = new int[arr.length][arr.length];
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (i < 2) {
                    result[i][j] = 2;
                } else {
                    result[i][j] =
                }
            }
        }
    }*/

}
