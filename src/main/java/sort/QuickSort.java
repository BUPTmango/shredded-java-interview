package sort;

import java.util.Arrays;

/**
 * ��������
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/15 12:49 ����
 */
public class QuickSort {

    public void quickSort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int p = partition(nums, start, end);
        sort(nums, start, end - 1);
        sort(nums, start + 1, end);
    }

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
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        quickSort.quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
