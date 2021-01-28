package producer_consumer;

import java.util.LinkedList;

/**
 * 使用wait和notifyAll来实现 生产者消费者模型(固定容量的同步容器)
 *
 * @author liu tangchen
 * @version 1.0
 * @date 2021/1/18 10:18 上午
 */
public class MyContainer1<T> {
    final private LinkedList<T> list = new LinkedList<>();
    /**
     * 容器最大容量
     */
    final private int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        // 注意此处用while而不是if
        while (list.size() == MAX) {
            try {
                this.wait(); // wait会主动释放锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        ++count;
        // 注意此处为notifyAll而不是notify,因为notify是随机唤醒一个线程，很可能又是一个生产者，接着wait继续执行，检查while条件，如果成立，wait,程序不动了。
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
