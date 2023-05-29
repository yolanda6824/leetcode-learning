import java.util.ArrayList;
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
}
