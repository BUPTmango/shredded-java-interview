package rate_limit;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

/**
 * 使用信号量实现限流
 * 注意以固定的时间频率释放信号量
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/3/27 7:29 上午
 */
public class SemaphoreTest {
    private static Semaphore semaphore = new Semaphore(10);

    public static void bizMethod() throws InterruptedException {
        if (!semaphore.tryAcquire()) {
            System.out.println(Thread.currentThread().getName() + "被拒绝");
            return;
        }

        System.out.println(Thread.currentThread().getName() + "执行业务逻辑");
        Thread.sleep(500);//模拟处理业务逻辑需要1秒
        semaphore.release();
    }

    public static void main(String[] args) {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                semaphore.release(10);
                System.out.println("释放所有锁");
            }
        }, 1000, 1000);

        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(10);//模拟每隔10ms就有1个请求进来
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
