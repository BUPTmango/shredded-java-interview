package sort;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * ��������
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/15 12:49 ����
 */
public class QuickSort {

    public void quickSort(int[] nums) {
//        sort(nums, 0, nums.length - 1);
        sort_iterate(nums, 0, nums.length - 1);
    }

    /**
     * �ݹ�ʵ��
     */
    private void sort(int[] nums, int start, int end) {
        // ע��Ҫд�˳�����
        if (start >= end) {
            return;
        }
        int p = partition(nums, start, end);
        sort(nums, start, p - 1);
        sort(nums, p + 1, end);
    }

    /**
     * �ǵݹ�ʵ�� �ֶ�ģ��ջ
     */
    private void sort_iterate(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        int i, j;
        // ע���������Һ���
        stack.push(end);
        stack.push(start);
        while (!stack.isEmpty()) {
            // ������ָ��
            i = stack.pop();
            /////////
            // ������ָ��
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
     * �ݹ�ʵ�ֺͷǵݹ�ʵ�ֹ��� partition ����
     */
    private int partition(int[] nums, int left, int right) {
        // ���ѡһ����Ϊpivot
        if (right > left) {
            int random = (int) (Math.random() * (right - left) + left + 1);
            int tmp = nums[left];
            nums[left] = nums[random];
            nums[random] = tmp;
        }
        int pivot = nums[left];
        while (left < right) {
            // nums[right] >= pivot ע�⣡������ ����Ҫ��= �������������ظ����ֵ�ʱ��ᷢ����ѭ��
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
