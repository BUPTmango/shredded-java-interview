package producer_consumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 利用ArrayBlockingQueue编写生产者消费者的demo
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/2/4 9:43 上午
 */
public class BlockingQueueTest {

    static class Producer implements Runnable {
        private BlockingQueue<String> queue;

        public Producer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            // 生产20条消息
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                    System.out.println("send message : " + "Message: " + i);
                    queue.put("Message: " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 发送结束消息
            try {
                System.out.println("time to say goodbye!!");
                queue.put("BYE");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {
        private BlockingQueue<String> queue;

        public Consumer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String msg;
            try {
                // 只要不是结束消息 就遍历
                while (!(msg = queue.take()).equals("BYE")) {
                    System.out.println("consumed message: " + msg);
                    Thread.sleep(new Random().nextInt(1000));
                }
                System.out.println("got bye message, good bye!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
