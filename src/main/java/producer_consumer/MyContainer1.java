package producer_consumer;

import java.util.LinkedList;

/**
 * ʹ��wait��notifyAll��ʵ�� ������������ģ��(�̶�������ͬ������)
 *
 * @author liu tangchen
 * @version 1.0
 * @date 2021/1/18 10:18 ����
 */
public class MyContainer1<T> {
    final private LinkedList<T> list = new LinkedList<>();
    /**
     * �����������
     */
    final private int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        // ע��˴���while������if
        while (list.size() == MAX) {
            try {
                this.wait(); // wait�������ͷ���
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        ++count;
        // ע��˴�ΪnotifyAll������notify,��Ϊnotify���������һ���̣߳��ܿ�������һ�������ߣ�����wait����ִ�У����while���������������wait,���򲻶��ˡ�
        this.notifyAll();
    }

    public synchronized T get() {
        T t = null;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }
}
