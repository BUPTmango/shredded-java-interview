package rate_limit;

import org.junit.Test;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现限流中的令牌桶算法
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/3/27 7:34 上午
 */
public class TokenBucket {
    /**
     * 定义的桶
     */
    public class Bucket {
        // 容量
        int capacity;
        // 速率，每秒放多少
        int rateCount;
        // 目前token个数
        AtomicInteger curCount = new AtomicInteger(0);

        public Bucket(int capacity, int rateCount) {
            this.capacity = capacity;
            this.rateCount = rateCount;
        }

        public void put() {
            if (curCount.get() < capacity) {
                System.out.println("目前数量==" + curCount.get() + ", 我还可以继续放");
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

        // 固定线程，固定的速率往桶里放数据，比如每秒N个
        ScheduledThreadPoolExecutor scheduledCheck = new ScheduledThreadPoolExecutor(1);
        scheduledCheck.scheduleAtFixedRate(() -> {
            bucket.put();
        }, 0, 1, TimeUnit.SECONDS);

        // 先等待一会儿，让桶里放点token
        Thread.sleep(6000);

        // 模拟瞬间10个线程进来拿token
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                if (bucket.get()) {
                    System.out.println(Thread.currentThread() + "获取到了资源");
                } else {
                    System.out.println(Thread.currentThread() + "被拒绝");
                }
            }).start();
        }

        // 等待，往桶里放token
        Thread.sleep(3000);

        // 继续瞬间10个线程进来拿token
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                if (bucket.get()) {
                    System.out.println(Thread.currentThread() + "获取到了资源");
                } else {
                    System.out.println(Thread.currentThread() + "被拒绝");
                }
            }).start();
        }
    }
}

