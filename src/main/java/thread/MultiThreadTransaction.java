package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 多线程事务 类似2PC
 * 描述：多线程分批插入，其中一个线程报错，所有线程回滚
 * 解决思路：
 * 先通过编程式事务开启事务，然后插入 10w 条数据后，但是不提 交。同时告诉主线程，我这边准备好了，进入等待。
 * 如果有一个线程出现了问题，那么设置全局变量为不可提交。 然后唤醒所有等待的子线程，进行回滚。
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2022/5/14 22:27
 */
public class MultiThreadTransaction {
    // 是否可以提交
    private static volatile boolean IS_OK = true;

    // 同时执行的线程数
    private static int threadCount = 5;

    public static void main(String[] args) {
        // 子线程等待主线程通知
        CountDownLatch mainMonitor = new CountDownLatch(1);
        CountDownLatch childMonitor = new CountDownLatch(threadCount);
        //子线程运行结果
        List<Boolean> childResponse = new ArrayList<>();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "：开始执行");
                    if (finalI == 4) {
                        throw new Exception("出现异常");
                    }
                    TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1000));
                    childResponse.add(Boolean.TRUE);
                    childMonitor.countDown();
                    System.out.println(Thread.currentThread().getName() + "：准备就绪,等待其他线程结果,判断是否事务提交");
                    // 阻塞等待主线程判断mainResult
                    mainMonitor.await();
                    if (IS_OK) {
                        System.out.println(Thread.currentThread().getName() + "：事务提交");
                    } else {
                        System.out.println(Thread.currentThread().getName() + "：事务回滚");
                    }
                } catch (Exception e) {
                    childResponse.add(Boolean.FALSE);
                    childMonitor.countDown();
                    System.out.println(Thread.currentThread().getName() + "：出现异常,开始事务回滚");
                }
            });
        }
        try {
            // 主线程等待所有子线程执行response
            childMonitor.await();
            for (Boolean resp : childResponse) {
                if (!resp) {
                    // 如果有一个子线程执行失败了，则改变mainResult，让所有子线程回滚
                    System.out.println(Thread.currentThread().getName() + ":有线程执行失败，标志位设置为false");
                    IS_OK = false;
                    break;
                }
            }
            // 主线程获取结果成功，让子线程开始根据主线程的结果执行（提交或回滚）
            mainMonitor.countDown();
            // 为了让主线程阻塞，让子线程执行。
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }
}

