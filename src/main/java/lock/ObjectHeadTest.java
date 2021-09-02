package lock;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * 测试synchronized锁升级过程中对象头锁标志位的变化
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/2/9 10:28 上午
 */
public class ObjectHeadTest {
    private static Object lock = new Object();

    private void printObjectHead(Object o) {
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

    @Test
    public void test1() {
        /**
         * 无锁    0 01
         */
        Object o = new Object();
        printObjectHead(o);
    }

    @Test
    public void test2() throws InterruptedException {
        /**
         * 偏向锁    1 01
         */
        // JVM默认延时加载偏向锁。这个延时的时间大概为4s左右，具体时间因机器而异。
        // 当前偏向锁并没有偏向任何线程。此时这个偏向锁正处于可偏向状态，准备好进行偏向了！你也可以理解为此时的偏向锁是一个特殊状态的无锁。
        // 想想为什么偏向锁会延迟？
        // 因为jvm 在启动的时候需要加载资源，这些对象加上偏向锁没有任何意义啊，减少了大量偏向锁撤销的成本；所以默认就把偏向锁延迟了4000ms；
        Thread.sleep(5000);
        Object o = new Object();
        printObjectHead(o);
    }

    @Test
    public void test3() throws InterruptedException {
        /**
         * 偏向锁    1 01
         */
        // 注意这里要先sleep 否则前4秒偏向锁是关闭的
        Thread.sleep(5000);
        Object obj = new Object();
        synchronized (obj) {
            printObjectHead(obj);
        }
    }

    @Test
    public void test4() throws InterruptedException {
        /**
         * 轻量级锁    0 00
         */

        // thread1 thread2 交替执行
        Thread thread1 = new Thread(task);
        thread1.start();

        Thread.sleep(2000);

        Thread thread2 = new Thread(task);
        thread2.start();

        thread1.join();
        thread2.join();
    }

    @Test
    public void test5() throws InterruptedException {
        /**
         * 重量级锁    0 10
         */
        // thread1 thread2 同时执行
        Thread thread1 = new Thread(task);
        thread1.start();

        Thread thread2 = new Thread(task);
        thread2.start();

        thread1.join();
        thread2.join();
    }

    Runnable task = () -> {
        synchronized (lock) {
            try {
                Thread.sleep(1000);
                printObjectHead(lock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
