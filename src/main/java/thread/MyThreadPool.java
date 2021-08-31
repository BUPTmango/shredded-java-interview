package thread;

import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ��д�����̳߳�
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/4/29 16:50
 */
public class MyThreadPool {

    /**
     * �����߳��� �� ����߳���
     * ע�����volatile��֤�ɼ���
     */
    private volatile int coreSize, maxSize;

    private final long keepAliveTime;

    /**
     * ��������
     */
    private final BlockingQueue<Runnable> queue;
    /**
     * ���洴����worker���ϣ�����֮��shutdown
     */
    private final HashSet<Worker> workers = new HashSet<>();

    /**
     * �Ƿ�ʱ
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
                System.out.println("��ǰ���д�С��" + queue.size());
                // ������񵽶�����
                if (!queue.offer(runnable)) {
                    // ������ʧ������������߳�
                    System.out.println("offer ʧ��,��ǰ�߳�����" + getPoolSize());
                    if (!addWorker(runnable)) {
                        reject();
                    }
                }
            }
        }
    }

    /**
     * ���̳߳�����������ִ����ɲŹر��̳߳�
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
     * ֱ�ӹر�
     */
    public void shutDownNow() {
        queue.clear();
        shutdown();
    }

    private void reject() {
        throw new RuntimeException("������С,��ǰ�߳�����" + getPoolSize() + " ���д�С��" + queue.size());
    }

    private boolean addWorker(Runnable runnable) {
        // �����ǰ�߳�������������򴴽�ʧ��
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
                    // �����߳�
                    if (task != null) {
                        task.run();
                        System.out.println("���н���,��ǰ�߳�����" + getPoolSize());
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
                System.out.println("�����̣߳���ǰ�߳�����" + getPoolSize());
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
                System.out.println("���:" + j);
                int index = 0;
                while (index++ < 1000) {
                }
            });
        }
        pool.shutdown();
    }
}
