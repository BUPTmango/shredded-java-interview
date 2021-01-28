package sort;

/**
 * ������
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/15 3:09 ����
 */
public class HeapSort {
    private static class MaxHeap {
        private int[] data;
        private int count;
        private int capacity;

        public MaxHeap(int capacity) {
            //�����Ǵ�����1��ʼ�洢��
            this.data = new int[capacity + 1];
            //�洢Ԫ�ص�����
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
         * ��������Ԫ��
         * ������ӵ�Ԫ�طŵ����������棬Ȼ��
         * ��sift up���������Լ����ϲ�Ԫ�ؽ��бȽϣ������ͽ���λ�ã�С�Ļ��ͽ���
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
         * �Ӷ���ȡ��Ԫ��
         * ����˵�Ԫ��ȡ��
         * Ȼ��������Ԫ�طŵ���һ��Ԫ�ص�λ��
         * ��sift down������ײ�Ԫ�ض����ϲ�Ԫ�ش���ô˭��ͺ�˭��
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
            //��������Ҫ��������Խ�������
            //k = 1��ʱ����Ǹ�����ˣ��Ͳ���Ҫ�ٽ����καȽ���
            while (k > 1 && data[k / 2] < data[k]) {
                swap(data, k / 2, k);
                //����һ��k
                k /= 2;
            }
        }

        private void siftDown(int k) {
            //�ж��ǲ����к��ӣ������ӾͿ϶��к��ӣ�����ȫ�������У�������ֻ���Һ���ȴû������
            while (2 * k <= count) {
                int j = 2 * k;//j��ʾ�ڴ���ѭ���У�data[k]��data[j]����λ��  Ĭ�Ϻ����ӽ���
                //�ж���û���Һ���
                if (j + 1 <= count && data[j + 1] > data[j]) {//���Һ��Ӳ����Һ��ӱ����Ӵ�
                    j += 1;//����Ϊ�Һ��ӵ�����
                }
                //�Ⱥ��ӻ��󣬾Ͳ��û���
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
