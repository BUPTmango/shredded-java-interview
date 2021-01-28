package producer_consumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock和Condition来实现 生产者消费者模型(固定容量的同步容器)
 *
 * @author liu tangchen
 * @version 1.0
 * @date 2021/1/18 10:22 上午
 */
public class MyContainer2<T> {
    final private LinkedList<T> list = new LinkedList<>();
    /**
     * 容器最大容量
     */
    final private int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        lock.lock();
        try {
            while (list.size() == MAX) {
                producer.await();
            }
            list.add(t);
            count++;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        lock.lock();
        try {
            while (list.size() == 0) {
                consumer.await();
            }
            t = list.removeFirst();
            count--;
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }
}

