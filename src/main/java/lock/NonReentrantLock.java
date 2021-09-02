package lock;

/**
 * 实现不可重入锁
 * 通过 标志位 + wait/notify 实现
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/9/2 23:31
 */
public class NonReentrantLock {
    /**
     * 通过标志位实现
     */
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        // 通过wait和notify的配合实现不可重入锁
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }

}
