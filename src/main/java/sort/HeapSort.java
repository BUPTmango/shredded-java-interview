package sort;

/**
 * 堆排序
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/15 3:09 下午
 */
public class HeapSort {
    private static class MaxHeap {
        private int[] data;
        private int count;
        private int capacity;

        public MaxHeap(int capacity) {
            //数组是从索引1开始存储的
            this.data = new int[capacity + 1];
            //存储元素的数量
            count = 0;
            this.capacity = capacity;
        }

        public int size() {
            return count;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        /**
         * 向堆中添加元素
         * 将新添加的元素放到数组的最后面，然后
         * 用sift up方法，将自己和上层元素进行比较，如果大就交换位置，小的话就结束
         *
         * @param item
         */
        public void insert(int item) {
            assert count + 1 <= capacity;
            data[count + 1] = item;
            count++;
            siftUp(count);
        }

        /**
         * 从堆中取出元素
         * 将最顶端的元素取出
         * 然后将最后面的元素放到第一个元素的位置
         * 用sift down，如果底层元素都比上层元素大，那么谁大就和谁换
         *
         * @return
         */
        public int extractMax() {
            int item = data[1];
            swap(data, 1, count);
            count--;
            siftDown(1);
            return item;
        }


        private void siftUp(int k) {
            //有索引就要考虑数组越界的问题
            //k = 1的时候就是根结点了，就不需要再进行任何比较了
            while (k > 1 && data[k / 2] < data[k]) {
                swap(data, k / 2, k);
                //更新一下k
                k /= 2;
            }
        }

        private void siftDown(int k) {
            //判断是不是有孩子，有左孩子就肯定有孩子，在完全二叉树中，不可能只有右孩子却没有左孩子
            while (2 * k <= count) {
                int j = 2 * k;//j表示在此轮循环中，data[k]和data[j]交换位置  默认和左孩子交换
                //判断有没有右孩子
                if (j + 1 <= count && data[j + 1] > data[j]) {//有右孩子并且右孩子比左孩子大
                    j += 1;//更新为右孩子的索引
                }
                //比孩子还大，就不用换了
                if (data[k] >= data[j]) {
                    break;
                }
                swap(data, k, j);
                k = j;
            }

        }

        private void swap(int[] arr, int i, int j) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
    }
}
