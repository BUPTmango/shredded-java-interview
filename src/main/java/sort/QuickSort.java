package sort;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * 快速排序
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/15 12:49 下午
 */
public class QuickSort {

    public void quickSort(int[] nums) {
//        sort(nums, 0, nums.length - 1);
        sort_iterate(nums, 0, nums.length - 1);
    }

    /**
     * 递归实现
     */
    private void sort(int[] nums, int start, int end) {
        // 注意要写退出条件
        if (start >= end) {
            return;
        }
        int p = partition(nums, start, end);
        sort(nums, start, p - 1);
        sort(nums, p + 1, end);
    }

    /**
     * 非递归实现 手动模拟栈
     */
    private void sort_iterate(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        int i, j;
        // 注意这里先右后左
        stack.push(end);
        stack.push(start);
        while (!stack.isEmpty()) {
            // 弹出左指针
            i = stack.pop();
            /////////
            // 弹出右指针
            j = stack.pop();
            if (i < j) {
                int  p = partition(nums, i, j);
                stack.push(p - 1);
                stack.push(i);
                stack.push(j);
                stack.push(p + 1);
            }
        }
    }

    /**
     * 递归实现和非递归实现共用 partition 方法
     */
    private int partition(int[] nums, int left, int right) {
        // 随机选一个作为pivot
        if (right > left) {
            int random = (int) (Math.random() * (right - left) + left + 1);
            int tmp = nums[left];
            nums[left] = nums[random];
            nums[random] = tmp;
        }
        int pivot = nums[left];
        while (left < right) {
            // nums[right] >= pivot 注意！！！！ 这里要有= 否则数组中有重复数字的时候会发生死循环
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = new Random().ints(1, 20).distinct().limit(10).toArray();
        quickSort.quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
