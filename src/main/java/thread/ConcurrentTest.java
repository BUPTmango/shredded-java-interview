package thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * 模拟并发测试接口或者方法
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/3/16 9:48 上午
 */
public class ConcurrentTest {

    private static final int MAX_THREADS = 10;

    private CountDownLatch cdl = new CountDownLatch(1);

    @Test
    public void testInterface() {
        for (int i = 0; i < MAX_THREADS; i++) {
            Thread t = new Thread(() -> {
                try {
                    cdl.await();
                    // 执行具体的接口或者方法
                    Thread.sleep(100);
                    System.out.println("method call success");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
        // 循环创建线程并start之后countdown，唤醒所有await的线程，实现并发测试
        cdl.countDown();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
