package tree;

import node.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 二叉树的前序遍历
 * 递归和非递归实现
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/28 3:50 下午
 */
public class PreorderTraversal {
    private List<Integer> list = new ArrayList<>();

    /**
     * 递归写法
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal_recursive(TreeNode root) {
        preorder(root);
        return list;
    }

    private void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * 非递归写法
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal_iterate(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            // 因为放入栈 所以左右要反过来
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }
}
