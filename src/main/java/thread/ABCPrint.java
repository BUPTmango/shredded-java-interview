package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程循环打印 ABC 10次
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/3/22 10:22 下午
 */
public class ABCPrint {

    private Lock lock = new ReentrantLock();

    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    private char currentThreadName = 'A';

    public static void main(String[] args) {
        ABCPrint print = new ABCPrint();

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(print.new ThreadA());
        service.execute(print.new ThreadB());
        service.execute(print.new ThreadC());

        service.shutdown();
    }

    private class ThreadA implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (currentThreadName != 'A') {
                        try {
                            conditionA.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("A");
                    currentThreadName = 'B';
                    conditionB.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
    private class ThreadB implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (currentThreadName != 'B') {
                        try {
                            conditionB.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("B");
                    currentThreadName = 'C';
                    conditionC.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
    private class ThreadC implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (currentThreadName != 'C') {
                        try {
                            conditionC.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("C");
                    currentThreadName = 'A';
                    conditionA.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

