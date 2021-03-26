package rate_limit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * ʹ��Guava��RateLimiterʵ������
 * ���ڲ�ʹ������Ͱ�㷨ʵ��
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/3/26 10:21 ����
 */
public class GuavaRateLimiterTest {
    /**
     * ����ÿ������10�����ƣ��൱��ÿ100ms����1������
     */
    private RateLimiter rateLimiter = RateLimiter.create(10);

    /**
     * ģ��ִ��ҵ�񷽷�
     */
    public void exeBiz() {
        if (rateLimiter.tryAcquire(1)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("�߳�" + Thread.currentThread().getName() + "��ִ��ҵ���߼�");
        } else {
            System.out.println("�߳�" + Thread.currentThread().getName() + "��������");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        GuavaRateLimiterTest limiterTest = new GuavaRateLimiterTest();
        // �ȴ�500ms����limiter����һЩ����
        Thread.sleep(500);

        // ģ��˲������100���߳�����
        for (int i = 0; i < 100; i++) {
            new Thread(limiterTest::exeBiz).start();
        }
    }
}

