package synchronizers;

import java.util.concurrent.*;

/**
 * CountDownLatch demo
 * 多线程计算质数的个数和单线程的比较
 *
 * bound = 100000000（1亿）的时候 执行时间比较：
 * 单线程：140863毫秒
 * 多线程：37674毫秒
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/2/4 3:18 下午
 */
public class CountDownLatchTest {
    static class Worker implements Callable<Integer> {
        private CountDownLatch latch;
        private int start;
        private int end;

        public Worker(CountDownLatch latch, int start, int end) {
            this.latch = latch;
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() {
            int count = 0;
            for (int i = start; i <= end; i++) {
                if (isPrime(i)) {
                    count++;
                }
            }
            latch.countDown();
            return count;
        }
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int cpus = Runtime.getRuntime().availableProcessors() + 1;
        CountDownLatch countDownLatch = new CountDownLatch(cpus);
        ExecutorService executorService = Executors.newFixedThreadPool(cpus);
        int bound = 10000000;
        int start = 1, end = 1, step = bound / cpus;
        int count = 0;
        long multiStart = System.currentTimeMillis();
        // 用CompletionService包装 方便通过take从blockingQueue中取
        CompletionService<Integer> service = new ExecutorCompletionService<>(executorService);
        for (int i = 0; i < cpus; i++) {
            end = Math.min(bound, start + step);
            service.submit(new Worker(countDownLatch, start, end - 1));
            start = end + 1;
        }
        countDownLatch.await();
        // 取值
        for (int i = 0; i < cpus; i++) {
            count += service.take().get();
        }
        System.out.println("multi : " + count);
        long multiEnd = System.currentTimeMillis();
        System.out.println("multi time : " + (multiEnd - multiStart));
        executorService.shutdown();

        int c = 0;
        long singleStart = System.currentTimeMillis();
        for (int i = 1; i <= bound; i++) {
            if (isPrime(i)) {
                c++;
            }
        }
        System.out.println("single : " + c);
        long singleEnd = System.currentTimeMillis();
        System.out.println("single time : " + (singleEnd - singleStart));
    }
}
