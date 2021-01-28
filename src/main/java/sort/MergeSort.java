package sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/15 5:28 下午
 */
public class MergeSort {

    public static void mergeSort(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        // 左边
        mergeSort(a, low, mid);
        // 右边
        mergeSort(a, mid + 1, high);
        // 左右归并
        merge(a, low, mid, high);
        System.out.println(Arrays.toString(a));
    }

    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        // 左指针
        int i = low;
        // 右指针
        int j = mid + 1;
        // 临时数组的索引
        int index = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[index++] = a[i++];
            } else {
                temp[index++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[index++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[index++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int k = 0; k < temp.length; k++) {
            a[k + low] = temp[k];
        }
    }

    public static void main(String[] args) {
        int a[] = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50};
        mergeSort(a, 0, a.length - 1);
        System.out.println("排序结果：" + Arrays.toString(a));
    }
}
