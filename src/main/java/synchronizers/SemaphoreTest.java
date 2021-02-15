package synchronizers;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore demo
 * ��ͻ��˷��ʳ���
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/2/4 4:56 ����
 */
public class SemaphoreTest {
    static class Request implements Runnable {
        private Semaphore semaphore;
        private int num;

        public Request(Semaphore semaphore, int num) {
            this.semaphore = semaphore;
            this.num = num;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(num + "��ȡ�����");
                Thread.sleep(new Random().nextInt(3000));
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // ֻ��3��requestͬʱ����
        Semaphore semaphore = new Semaphore(3);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i <= 20; i++) {
            executorService.submit(new Request(semaphore, i));
        }
        executorService.shutdown();
    }
}
