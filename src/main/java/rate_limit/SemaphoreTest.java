package rate_limit;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

/**
 * ʹ���ź���ʵ������
 * ע���Թ̶���ʱ��Ƶ���ͷ��ź���
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/3/27 7:29 ����
 */
public class SemaphoreTest {
    private static Semaphore semaphore = new Semaphore(10);

    public static void bizMethod() throws InterruptedException {
        if (!semaphore.tryAcquire()) {
            System.out.println(Thread.currentThread().getName() + "���ܾ�");
            return;
        }

        System.out.println(Thread.currentThread().getName() + "ִ��ҵ���߼�");
        // ģ�⴦��ҵ���߼���Ҫ1��
        Thread.sleep(500);
        semaphore.release();
    }

    public static void main(String[] args) {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                semaphore.release(10);
                System.out.println("�ͷ�������");
            }
        }, 1000, 1000);

        for (int i = 0; i < 10000; i++) {
            try {
                // ģ��ÿ��10ms����1���������
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(() -> {
                try {
                    SemaphoreTest.bizMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
