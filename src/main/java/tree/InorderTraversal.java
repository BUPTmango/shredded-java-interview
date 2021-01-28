package tree;

import node.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 二叉树的中序遍历
 * 递归和非递归实现
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/28 4:16 下午
 */
public class InorderTraversal {
    private List<Integer> list = new ArrayList<>();

    /**
     * 递归写法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_recursive(TreeNode root) {
        inorder(root);
        return list;
    }
    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }

    /**
     * 非递归写法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_iterate(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            // 先尽可能找到左边的节点
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 打印
            root = stack.pop();
            res.add(root.val);
            // 继续寻找右边的
            root = root.right;
        }
        return res;
    }
}
