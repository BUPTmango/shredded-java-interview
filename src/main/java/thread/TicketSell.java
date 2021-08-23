package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程卖票
 *
 * 1.编写一个多线程程序模拟卖票的过程，满足以下条件
 * 2.4个窗口编号1-4卖出30张票编号1-30
 * 3.2号窗口只能卖编号为偶数的票
 * 4.每张票卖出的时间为100~300ms随机
 * 5.再窗口卖完一张票之前，不能绑定窗口与下一张票的关系
 * 6.卖票情况打印出来，共30行，每行格式： 编号X的窗口卖出了编号Y的票.
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/8/23 22:03
 */
public class TicketSell {
    public static void main(String[] args) {
        AtomicInteger num = new AtomicInteger(0);
        Object lock = new Object();
        Thread thread1 = new Thread(new Worker(num, 1, lock));
        Thread thread2 = new Thread(new Worker(num, 2, lock));
        Thread thread3 = new Thread(new Worker(num, 3, lock));
        Thread thread4 = new Thread(new Worker(num, 4, lock));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

class Worker implements Runnable {

    AtomicInteger num = null;
    int id = 0;
    Object lock;

    public Worker(AtomicInteger num, int id, Object lock) {
        this.num = num;
        this.id = id;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (num.get() < 30) {
            int n = -1;
            if (id == 2) {
                if (num.get() % 2 == 0) {
                    n = getNum();
                }
            } else {
                n = getNum();
            }
            if (n != -1) {
                sell(n);
            }
        }
    }

    /**
     * 只锁住发票部分，后面sleep不锁，降低锁粒度
     *
     * @return
     */
    private int getNum() {
        synchronized (lock) {
            if (num.get() >= 30) {
                return -1;
            }
            return num.incrementAndGet();
        }
    }

    private void sell(int n) {
        try {
            Thread.sleep(((int) Math.random() * 2 + 1) * 100);
            System.out.println("编号" + id + "的窗口卖出了编号" + n + "的票");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
