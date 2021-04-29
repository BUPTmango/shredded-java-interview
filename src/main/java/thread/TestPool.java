package thread;

import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ��д�����̳߳�
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/4/29 16:50
 */
public class TestPool {

    /**
     * �����߳��� �� ����߳���
     */
    private int coreSize, maxSize;
    /**
     * ͳ�������е��̸߳���
     */
    private AtomicInteger running = new AtomicInteger(0);
    /**
     * ��������
     */
    private BlockingQueue<Runnable> queue;
    /**
     * ���洴����worker���ϣ�����֮��shutdown
     */
    private HashSet<Worker> workers = new HashSet<>();

    public TestPool(int coreSize, int maxSize, BlockingQueue<Runnable> queue) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.queue = queue;
    }

    public void execute(Runnable runnable) {
        if (running.get() < coreSize) {
            if (!addWorker(runnable)) {
                reject();
            }
        } else {
            System.out.println("��ǰ���д�С��" + queue.size());
            // ������񵽶�����
            if (!queue.offer(runnable)) {
                // ������ʧ������������߳�
                System.out.println("offer ʧ��,��ǰ�߳�����" + running.get());
                if (!addWorker(runnable)) {
                    reject();
                }
            }
        }
    }

    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    private void reject() {
        throw new RuntimeException("������С,��ǰ�߳�����" + running.get() + " ���д�С��" + queue.size());
    }

    private boolean addWorker(Runnable runnable) {
        // �����ǰ�߳�������������򴴽�ʧ��
        if (running.get() >= maxSize) {
            return false;
        }
        Worker worker = new Worker(runnable);
        workers.add(worker);
        worker.start();
        return true;
    }


    private class Worker extends Thread {
        private Runnable runnable;
        /**
         * ��ʾ�Ƿ����и�worker
         */
        private volatile boolean isRun = true;

        public Worker(Runnable runnable) {
            this.runnable = runnable;
            // �����߳������߳���
            System.out.println("�����߳�:��ǰ�߳���:" + running.incrementAndGet());
        }

        @Override
        public void run() {
            try {
                while (isRun) {
                    // �����߳�
                    runnable.run();
                    System.out.println("���н���,��ǰ�߳�����" + running.get());
                    // �����ǰ�����߳������ں��Ĵ�С���˳��߳�
                    if (running.get() > coreSize) {
                        break;
                    } else {
                        // ��֮�Ӷ�����ȡ���ݣ����������ٴ��coreSize���߳�
                        try {
                            System.out.println("000000�����д�С��" + queue.size());
                            runnable = queue.take();
                            System.out.println("11111111�����д�С��" + queue.size());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } finally {
                // �߳̽���������������
                running.decrementAndGet();
                System.out.println("�����̣߳���ǰ�߳�����" + running.get());
            }
        }

        public void shutdown() {
            isRun = false;
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("main");
        TestPool pool = new TestPool(2, 5, new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 100; i++) {
            final int j = i;
            System.out.println("i=" + i + " " + Thread.currentThread().getName());
            pool.execute(() -> {
                try {
                    System.out.println("˯0.1�� ���:" + j);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            pool.shutdown();
        }
    }
}
