package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ���߳���Ʊ
 *
 * 1.��дһ�����̳߳���ģ����Ʊ�Ĺ��̣�������������
 * 2.4�����ڱ��1-4����30��Ʊ���1-30
 * 3.2�Ŵ���ֻ�������Ϊż����Ʊ
 * 4.ÿ��Ʊ������ʱ��Ϊ100~300ms���
 * 5.�ٴ�������һ��Ʊ֮ǰ�����ܰ󶨴�������һ��Ʊ�Ĺ�ϵ
 * 6.��Ʊ�����ӡ��������30�У�ÿ�и�ʽ�� ���X�Ĵ��������˱��Y��Ʊ.
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
     * ֻ��ס��Ʊ���֣�����sleep����������������
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
            System.out.println("���" + id + "�Ĵ��������˱��" + n + "��Ʊ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
