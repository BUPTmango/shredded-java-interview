package lock;

/**
 * 死锁 demo 可以通过 jps + jstack 的方式查看
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/2/3 9:00 下午
 */
public class DeadLockSample extends Thread {

    private final Object a;
    private final Object b;

    public DeadLockSample(Object a, Object b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (a) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b) {
                System.out.println("success");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        DeadLockSample d1 = new DeadLockSample(a, b);
        DeadLockSample d2 = new DeadLockSample(b, a);
        d1.start();
        d2.start();
        d1.join();
        d2.join();
    }
}
