package rate_limit;

import org.junit.Test;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ʵ�������е�����Ͱ�㷨
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/3/27 7:34 ����
 */
public class TokenBucket {
    /**
     * �����Ͱ
     */
    public class Bucket {
        // ����
        int capacity;
        // ���ʣ�ÿ��Ŷ���
        int rateCount;
        // Ŀǰtoken����
        AtomicInteger curCount = new AtomicInteger(0);

        public Bucket(int capacity, int rateCount) {
            this.capacity = capacity;
            this.rateCount = rateCount;
        }

        public void put() {
            if (curCount.get() < capacity) {
                System.out.println("Ŀǰ����==" + curCount.get() + ", �һ����Լ�����");
                curCount.addAndGet(rateCount);
            }
        }

        public boolean get() {
            if (curCount.get() >= 1) {
                curCount.decrementAndGet();
                return true;
            }
            return false;
        }
    }

    @Test
    public void testTokenBucket() throws InterruptedException {

        Bucket bucket = new Bucket(5, 2);

        // �̶��̣߳��̶���������Ͱ������ݣ�����ÿ��N��
        ScheduledThreadPoolExecutor scheduledCheck = new ScheduledThreadPoolExecutor(1);
        scheduledCheck.scheduleAtFixedRate(() -> {
            bucket.put();
        }, 0, 1, TimeUnit.SECONDS);

        // �ȵȴ�һ�������Ͱ��ŵ�token
        Thread.sleep(6000);

        // ģ��˲��10���߳̽�����token
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                if (bucket.get()) {
                    System.out.println(Thread.currentThread() + "��ȡ������Դ");
                } else {
                    System.out.println(Thread.currentThread() + "���ܾ�");
                }
            }).start();
        }

        // �ȴ�����Ͱ���token
        Thread.sleep(3000);

        // ����˲��10���߳̽�����token
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                if (bucket.get()) {
                    System.out.println(Thread.currentThread() + "��ȡ������Դ");
                } else {
                    System.out.println(Thread.currentThread() + "���ܾ�");
                }
            }).start();
        }
    }
}

