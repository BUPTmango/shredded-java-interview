package sort;

import java.util.Arrays;

/**
 * �鲢����
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/15 5:28 ����
 */
public class MergeSort {

    public static void mergeSort(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        // ���
        mergeSort(a, low, mid);
        // �ұ�
        mergeSort(a, mid + 1, high);
        // ���ҹ鲢
        merge(a, low, mid, high);
        System.out.println(Arrays.toString(a));
    }

    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        // ��ָ��
        int i = low;
        // ��ָ��
        int j = mid + 1;
        // ��ʱ���������
        int index = 0;
        // �ѽ�С�������Ƶ���������
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[index++] = a[i++];
            } else {
                temp[index++] = a[j++];
            }
        }
        // �����ʣ�������������
        while (i <= mid) {
            temp[index++] = a[i++];
        }
        // ���ұ߱�ʣ�������������
        while (j <= high) {
            temp[index++] = a[j++];
        }
        // ���������е�������nums����
        for (int k = 0; k < temp.length; k++) {
            a[k + low] = temp[k];
        }
    }

    public static void main(String[] args) {
        int a[] = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50};
        mergeSort(a, 0, a.length - 1);
        System.out.println("��������" + Arrays.toString(a));
    }
}
