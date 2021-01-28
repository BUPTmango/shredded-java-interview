package tree;

import node.TreeNode;

import java.util.*;

/**
 * 二叉树的后序遍历
 * 递归和非递归实现
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/28 4:21 下午
 */
public class PostorderTraversal {
    private List<Integer> list = new ArrayList<>();

    /**
     * 递归写法
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal_recursive(TreeNode root) {
        postorder(root);
        return list;
    }

    private void postorder(TreeNode root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        list.add(root.val);
    }

    /**
     * 非递归写法
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal_iterate(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        // 两个地方和前序不一样
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 不一样的第一个地方 在list头部加元素值
            res.addFirst(node.val);
            // 不一样的第二个地方 先left后right
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return res;
    }
}
