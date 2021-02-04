package concurrenttool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier demo
 * 吃饭场景
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/2/4 4:39 下午
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
                System.out.println(sdf.format(new Date()) + " " + name + "出发去饭店");
                Thread.sleep(random.nextInt(3000));
                System.out.println(sdf.format(new Date()) + " " + name + "到了饭店");

                barrier.await();

                System.out.println(sdf.format(new Date()) + " " + name + "开始吃饭");
                Thread.sleep(random.nextInt(3000));
                System.out.println(sdf.format(new Date()) + " " + name + "吃完了");

                // 重用CyclicBarrier
                barrier.await();

                System.out.println(sdf.format(new Date()) + " " + name + "离开餐厅");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3);

        List<Thread> threads = new ArrayList<>(3);
        threads.add(new Thread(new Student(barrier, "张三")));
        threads.add(new Thread(new Student(barrier, "李四")));
        threads.add(new Thread(new Student(barrier, "王五")));

        for (Thread thread : threads) {
            thread.start();
        }

        // 等待所有线程跑完
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
