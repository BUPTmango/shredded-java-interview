package concurrenttool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier demo
 * �Է�����
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/2/4 4:39 ����
 */
public class CyclicBarrierTest {
    static class Student implements Runnable {
        private CyclicBarrier barrier;
        private String name;

        public Student(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                Random random = new Random();

                Thread.sleep(random.nextInt(3000));
                System.out.println(sdf.format(new Date()) + " " + name + "����ȥ����");
                Thread.sleep(random.nextInt(3000));
                System.out.println(sdf.format(new Date()) + " " + name + "���˷���");

                barrier.await();

                System.out.println(sdf.format(new Date()) + " " + name + "��ʼ�Է�");
                Thread.sleep(random.nextInt(3000));
                System.out.println(sdf.format(new Date()) + " " + name + "������");

                // ����CyclicBarrier
                barrier.await();

                System.out.println(sdf.format(new Date()) + " " + name + "�뿪����");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3);

        List<Thread> threads = new ArrayList<>(3);
        threads.add(new Thread(new Student(barrier, "����")));
        threads.add(new Thread(new Student(barrier, "����")));
        threads.add(new Thread(new Student(barrier, "����")));

        for (Thread thread : threads) {
            thread.start();
        }

        // �ȴ������߳�����
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
