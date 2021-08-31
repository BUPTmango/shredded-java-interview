package thread;

import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 手写简易线程池
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/4/29 16:50
 */
public class MyThreadPool {

    /**
     * 核心线程数 和 最大线程数
     * 注意添加volatile保证可见行
     */
    private volatile int coreSize, maxSize;

    private final long keepAliveTime;

    /**
     * 阻塞队列
     */
    private final BlockingQueue<Runnable> queue;
    /**
     * 保存创建的worker集合，方便之后shutdown
     */
    private final HashSet<Worker> workers = new HashSet<>();

    /**
     * 是否超时
     */
    private volatile boolean allowCoreThreadTimeOut;

    public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
    }

    public int getPoolSize() {
        return workers.size();
    }

    public MyThreadPool(int coreSize, int maxSize, BlockingQueue<Runnable> queue, long keepAliveTime, TimeUnit unit) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.queue = queue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
    }

    public void execute(Runnable runnable) {
        synchronized (workers) {
            if (getPoolSize() < coreSize) {
                if (!addWorker(runnable)) {
                    reject();
                }
            } else {
                System.out.println("当前队列大小：" + queue.size());
                // 添加任务到队列中
                if (!queue.offer(runnable)) {
                    // 如果添加失败则继续创建线程
                    System.out.println("offer 失败,当前线程数：" + getPoolSize());
                    if (!addWorker(runnable)) {
                        reject();
                    }
                }
            }
        }
    }

    /**
     * 等线程池中所有任务都执行完成才关闭线程池
     */
    public void shutdown() {
        while (!queue.isEmpty()) {
        }
        for (Worker worker : workers) {
            worker.interrupt();
        }
        System.out.println("shutDown...finish...");
    }

    /**
     * 直接关闭
     */
    public void shutDownNow() {
        queue.clear();
        shutdown();
    }

    private void reject() {
        throw new RuntimeException("超出大小,当前线程数：" + getPoolSize() + " 队列大小：" + queue.size());
    }

    private boolean addWorker(Runnable runnable) {
        // 如果当前线程数大于最大数则创建失败
        if (getPoolSize() >= maxSize) {
            return false;
        }
        Worker worker = new Worker(runnable);
        worker.start();
        workers.add(worker);
        return true;
    }


    private class Worker extends Thread {

        private final Runnable firstTask;

        public Worker(Runnable task) {
            this.firstTask = task;
        }

        @Override
        public void run() {
            try {
                if (firstTask != null) {
                    firstTask.run();
                }
                while (true) {
                    boolean timed = getPoolSize() > coreSize && allowCoreThreadTimeOut;
                    Runnable task = timed ? queue.poll(keepAliveTime, TimeUnit.SECONDS) : queue.take();
                    // 运行线程
                    if (task != null) {
                        task.run();
                        System.out.println("运行结束,当前线程数：" + getPoolSize());
                        continue;
                    }
                    synchronized (workers) {
                        if (getPoolSize() > coreSize && allowCoreThreadTimeOut) {
                            workers.remove(this);
                            break;
                        }
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("take....InterruptedException");
            } finally {
                System.out.println("结束线程，当前线程数：" + getPoolSize());
            }
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("main");
        MyThreadPool pool = new MyThreadPool(2, 5, new ArrayBlockingQueue<>(10), 1, TimeUnit.SECONDS);
        for (int i = 0; i < 100; i++) {
            final int j = i;
            System.out.println("i=" + i + " " + Thread.currentThread().getName());
            pool.execute(() -> {
                System.out.println("完成:" + j);
                int index = 0;
                while (index++ < 1000) {
                }
            });
        }
        pool.shutdown();
    }
}
